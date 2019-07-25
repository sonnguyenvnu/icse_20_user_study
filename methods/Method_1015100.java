@Override public MessagePayload encode(){
  MessagePayload payload=super.encode();
  payload.searchableContent="[??]";
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  thumbnail.compress(Bitmap.CompressFormat.JPEG,75,baos);
  payload.binaryContent=baos.toByteArray();
  return payload;
}
