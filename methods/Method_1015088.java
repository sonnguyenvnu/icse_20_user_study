@Override public MessagePayload encode(){
  MessagePayload payload=super.encode();
  payload.searchableContent="[??]";
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("duration",duration);
    payload.content=objWrite.toString();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
