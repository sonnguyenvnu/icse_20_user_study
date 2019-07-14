public byte[] getByteResult(){
  if (byteResult != null) {
    return byteResult;
  }
  if (stringResult != null) {
    return stringResult.getBytes();
  }
  return null;
}
