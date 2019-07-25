@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("o",operator);
    payload.binaryContent=objWrite.toString().getBytes();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
