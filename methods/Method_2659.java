@Override public String toString(int[] ids){
  byte[] bytes=new byte[ids.length];
  for (int i=0; i < ids.length; i++) {
    bytes[i]=(byte)ids[i];
  }
  try {
    return new String(bytes,"UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
    return null;
  }
}
