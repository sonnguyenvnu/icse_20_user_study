public short[] allocEncodingHash(int suggestedSize){
  short[] buf=encodingHash;
  if (buf == null || buf.length < suggestedSize) {
    buf=new short[suggestedSize];
  }
 else {
    encodingHash=null;
  }
  return buf;
}
