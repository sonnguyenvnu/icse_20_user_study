@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  payload.searchableContent=title;
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  thumbnail.compress(Bitmap.CompressFormat.JPEG,75,baos);
  payload.binaryContent=baos.toByteArray();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("lat",location.getLatitude());
    objWrite.put("long",location.getLongitude());
    payload.content=objWrite.toString();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
