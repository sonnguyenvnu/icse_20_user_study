@Override public void decode(MessagePayload payload){
  try {
    if (payload.content != null) {
      JSONObject jsonObject=new JSONObject(payload.content);
      groupId=jsonObject.optString("g");
      operator=jsonObject.optString("m");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
