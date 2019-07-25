@Override public void decode(MessagePayload payload){
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      invitor=jsonObject.optString("o");
      groupId=jsonObject.optString("g");
      JSONArray jsonArray=jsonObject.optJSONArray("ms");
      invitees=new ArrayList<>();
      if (jsonArray != null) {
        for (int i=0; i < jsonArray.length(); i++) {
          invitees.add(jsonArray.getString(i));
        }
      }
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
