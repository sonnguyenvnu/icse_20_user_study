@Override public void decode(MessagePayload payload){
  title=payload.searchableContent;
  if (payload.binaryContent != null) {
    thumbnail=BitmapFactory.decodeByteArray(payload.binaryContent,0,payload.binaryContent.length);
  }
  try {
    if (payload.content != null) {
      JSONObject jsonObject=new JSONObject(payload.content);
      content=jsonObject.optString("c");
      url=jsonObject.optString("u");
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
