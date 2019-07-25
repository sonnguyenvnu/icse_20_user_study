@Override public void decode(MessagePayload payload){
  try {
    if (payload.binaryContent != null) {
      JSONObject jsonObject=new JSONObject(new String(payload.binaryContent));
      groupId=jsonObject.optString("g");
      operator=jsonObject.optString("o");
      JSONArray jsonArray=jsonObject.optJSONArray("ms");
      kickedMembers=new ArrayList<>();
      if (jsonArray != null) {
        for (int i=0; i < jsonArray.length(); i++) {
          kickedMembers.add(jsonArray.getString(i));
        }
      }
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
