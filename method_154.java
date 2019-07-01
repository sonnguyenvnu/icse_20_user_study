@Override public byte[] _XXXXX_(){
  if (null != value) {
    return value;
  }
  if (ByteString.EMPTY == request.getValue()) {
    value=null;
  }
 else {
    value=request.getValue().toByteArray();
  }
  return value;
}