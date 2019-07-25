@Override public void decode(MessagePayload payload){
  super.decode(payload);
  if (payload.binaryContent != null) {
    thumbnail=BitmapFactory.decodeByteArray(payload.binaryContent,0,payload.binaryContent.length);
  }
}
