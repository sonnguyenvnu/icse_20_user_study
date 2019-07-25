@Override public void decode(MessagePayload payload){
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      groupId=jsonObject.optString("g");
      operateUser=jsonObject.optString("o");
      alias=jsonObject.optString("n");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
