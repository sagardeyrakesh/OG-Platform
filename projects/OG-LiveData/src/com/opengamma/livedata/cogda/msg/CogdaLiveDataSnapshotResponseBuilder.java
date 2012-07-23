/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

/**
 * 
 */
public class CogdaLiveDataSnapshotResponseBuilder implements FudgeBuilder<CogdaLiveDataSnapshotResponseMessage> {

  public static MutableFudgeMsg buildMessageStatic(FudgeSerializer serializer, CogdaLiveDataSnapshotResponseMessage response) {
    MutableFudgeMsg msg = serializer.newMessage();
    CogdaLiveDataBuilderUtil.addResponseFields(msg, response);
    return msg;
  }

  @Override
  public MutableFudgeMsg buildMessage(FudgeSerializer serializer, CogdaLiveDataSnapshotResponseMessage object) {
    return buildMessageStatic(serializer, object);
  }

  public static CogdaLiveDataSnapshotResponseMessage buildObjectStatic(FudgeDeserializer deserializer, FudgeMsg message) {
    CogdaLiveDataSnapshotResponseMessage response = new CogdaLiveDataSnapshotResponseMessage();
    CogdaLiveDataBuilderUtil.setResponseFields(message, response);
    return response;
  }
  
  @Override
  public CogdaLiveDataSnapshotResponseMessage buildObject(FudgeDeserializer deserializer, FudgeMsg message) {
    return buildObjectStatic(deserializer, message);
  }

}
