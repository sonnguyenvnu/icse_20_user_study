@Override public void decode(MessagePayload payload){
  super.decode(payload);
  try {
    if (payload.content != null) {
      JSONObject jsonObject=new JSONObject(payload.content);
      duration=jsonObject.optInt("duration");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
