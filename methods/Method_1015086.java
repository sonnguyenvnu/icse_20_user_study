@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("o",operator);
    objWrite.put("m",newOwner);
    payload.content=objWrite.toString();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return null;
}
