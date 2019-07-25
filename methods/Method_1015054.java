@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.content=callId;
  try {
    JSONObject objWrite=new JSONObject();
    if (connectTime > 0) {
      objWrite.put("c",connectTime);
    }
    if (endTime > 0) {
      objWrite.put("e",endTime);
    }
    if (status > 0) {
      objWrite.put("s",status);
    }
    objWrite.put("t",targetId);
    objWrite.put("a",audioOnly ? 1 : 0);
    payload.binaryContent=objWrite.toString().getBytes();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
