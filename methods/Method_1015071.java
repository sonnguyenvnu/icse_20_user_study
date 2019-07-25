@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("o",creator);
    objWrite.put("n",groupName);
    payload.binaryContent=objWrite.toString().getBytes();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
