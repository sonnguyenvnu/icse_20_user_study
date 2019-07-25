@Override public byte[] generate(){
  return Misc.getBytesUTF8(String.valueOf(increment()));
}
