private static int indexOfEos(byte[] data,int fromIndex,int encoding){
  int terminationPos=indexOfZeroByte(data,fromIndex);
  if (encoding == ID3_TEXT_ENCODING_ISO_8859_1 || encoding == ID3_TEXT_ENCODING_UTF_8) {
    return terminationPos;
  }
  while (terminationPos < data.length - 1) {
    if (terminationPos % 2 == 0 && data[terminationPos + 1] == (byte)0) {
      return terminationPos;
    }
    terminationPos=indexOfZeroByte(data,terminationPos + 1);
  }
  return data.length;
}
