@Override public void decode(MessagePayload payload){
  super.decode(payload);
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      width=jsonObject.optInt("x");
      height=jsonObject.optInt("y");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
