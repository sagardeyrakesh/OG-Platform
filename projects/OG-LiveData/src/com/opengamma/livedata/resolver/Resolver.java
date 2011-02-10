/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.livedata.resolver;

import java.util.Collection;
import java.util.Map;

/**
 * Abstract base interface for resolvers.
 * 
 * @param <A> unresolved object 
 * @param <B> resolved object
 */
public interface Resolver<A, B> {
  
  B resolve(A spec);
  
  Map<A, B> resolve(Collection<A> specs);
}
