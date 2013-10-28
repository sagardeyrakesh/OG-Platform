/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.graph;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.opengamma.core.position.PositionOrTrade;
import com.opengamma.core.security.Security;
import com.opengamma.id.ObjectId;
import com.opengamma.sesame.config.ColumnOutput;
import com.opengamma.sesame.config.ViewColumn;
import com.opengamma.sesame.config.ViewDef;
import com.opengamma.sesame.function.FunctionRepo;
import com.opengamma.sesame.function.NoOutputFunction;
import com.opengamma.sesame.function.OutputFunction;

/**
 * Lightweight model of the functions needed to generate the outputs for a view.
 */
public final class Graph {

  // this is actually Map<String, Map<ObjectId, PortfolioOutputFunction<?, ?>>> but even an unsafe cast won't work
  private final Map<String, Map<ObjectId, FunctionTree<?>>> _functionTrees;

  private Graph(Map<String, Map<ObjectId, FunctionTree<?>>> functionTrees) {
    _functionTrees = functionTrees;
  }

  public static Graph forView(ViewDef viewDef,
                              Collection<? extends PositionOrTrade> targets,
                              Map<Class<?>, Object> infrastructure,
                              FunctionRepo functionRepo) {
    ImmutableMap.Builder<String, Map<ObjectId, FunctionTree<?>>> builder = ImmutableMap.builder();
    for (ViewColumn column : viewDef.getColumns()) {
      ImmutableMap.Builder<ObjectId, FunctionTree<?>> columnBuilder = ImmutableMap.builder();
      Map<Class<?>, ColumnOutput> requirements = column.getRequirements();
      for (PositionOrTrade positionOrTrade : targets) {
        FunctionTree<?> functionTree;
        if (requirements.containsKey(positionOrTrade.getClass())) {
          ColumnOutput requirement = requirements.get(positionOrTrade.getClass());
          Class<?> outputFunctionType = functionRepo.getFunctionType(requirement.getOutputName(), positionOrTrade.getClass());
          functionTree = FunctionTree.forFunction(outputFunctionType, requirement.getFunctionConfig(), infrastructure.keySet());
          columnBuilder.put(positionOrTrade.getUniqueId().getObjectId(), functionTree);
        } else if (requirements.containsKey(positionOrTrade.getSecurity().getClass())) {
          Security security = positionOrTrade.getSecurity();
          ColumnOutput requirement = requirements.get(security.getClass());
          Class<?> outputFunctionType = functionRepo.getFunctionType(requirement.getOutputName(), security.getClass());
          FunctionTree<?> securityTree = FunctionTree.forFunction(outputFunctionType, requirement.getFunctionConfig(), infrastructure.keySet());
          functionTree = SecurityFunctionDecorator.decorateRoot(securityTree);
        } else {
          functionTree = FunctionTree.forFunction(NoOutputFunction.class);
        }
        // TODO how will this work for in-memory trades? assign an ID?
        columnBuilder.put(positionOrTrade.getUniqueId().getObjectId(), functionTree);
      }
      builder.put(column.getName(), columnBuilder.build());
    }
    return new Graph(builder.build());
  }

  /**
   * @param infrastructure The engine infrastructure
   * @return A graph containing the built function instances
   */
  public FunctionGraph build(Map<Class<?>, Object> infrastructure) {
    ImmutableMap.Builder<String, Map<ObjectId, OutputFunction<?, ?>>> builder = ImmutableMap.builder();
    for (Map.Entry<String, Map<ObjectId, FunctionTree<?>>> entry : _functionTrees.entrySet()) {
      Map<ObjectId, FunctionTree<?>> functionsByTargetId = entry.getValue();
      ImmutableMap.Builder<ObjectId, OutputFunction<?, ?>> columnBuilder = ImmutableMap.builder();
      for (Map.Entry<ObjectId, FunctionTree<?>> columnEntry : functionsByTargetId.entrySet()) {
        ObjectId targetId = columnEntry.getKey();
        FunctionTree<?> functionTree = columnEntry.getValue();
        columnBuilder.put(targetId, (OutputFunction<?, ?>) functionTree.build(infrastructure));
      }
      String columnName = entry.getKey();
      builder.put(columnName, columnBuilder.build());
    }
    return new FunctionGraph(builder.build());
  }
}
