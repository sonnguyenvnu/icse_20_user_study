@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("m",operator);
    payload.content=objWrite.toString();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
