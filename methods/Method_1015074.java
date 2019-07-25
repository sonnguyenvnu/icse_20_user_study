@Override public void decode(MessagePayload payload){
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      groupId=jsonObject.optString("g");
      operator=jsonObject.optString("o");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
