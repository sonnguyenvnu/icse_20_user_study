@Override public int[] toIdList(String key){
  byte[] bytes=key.getBytes(UTF_8);
  int[] res=new int[bytes.length];
  for (int i=0; i < res.length; i++) {
    res[i]=bytes[i] & 0xFF;
  }
  if ((res.length == 1) && (res[0] == 0)) {
    return EMPTYLIST;
  }
  return res;
}
