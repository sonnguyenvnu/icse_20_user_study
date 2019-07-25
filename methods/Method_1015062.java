@Override public void decode(MessagePayload payload){
  if (payload.binaryContent != null) {
    thumbnail=BitmapFactory.decodeByteArray(payload.binaryContent,0,payload.binaryContent.length);
  }
  title=payload.searchableContent;
  try {
    if (payload.content != null) {
      JSONObject jsonObject=new JSONObject(payload.content);
      location.setLatitude(jsonObject.optDouble("lat"));
      location.setLongitude(jsonObject.optDouble("long"));
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
