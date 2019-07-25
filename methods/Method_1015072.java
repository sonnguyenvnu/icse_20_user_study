@Override public void decode(MessagePayload payload){
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      groupId=jsonObject.optString("g");
      creator=jsonObject.optString("o");
      groupName=jsonObject.optString("n");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
