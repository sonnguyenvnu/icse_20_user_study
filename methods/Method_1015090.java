@Override public MessagePayload encode(){
  MessagePayload payload=super.encode();
  payload.searchableContent="[????]";
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("x",width);
    objWrite.put("y",height);
    payload.binaryContent=objWrite.toString().getBytes();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
