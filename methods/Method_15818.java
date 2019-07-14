@Override public byte[] asBytes(){
  if (data == null) {
    data=UnCheck.unCheck(() -> StreamUtils.copyToByteArray(inputStream));
  }
  return data;
}
