@Override public void decode(MessagePayload payload){
  callId=payload.content;
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      connectTime=jsonObject.optLong("c",0);
      endTime=jsonObject.optLong("e",0);
      status=jsonObject.optInt("s",0);
      targetId=jsonObject.optString("t");
      audioOnly=jsonObject.optInt("a") > 0;
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
