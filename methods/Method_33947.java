boolean safeArrayEquals(byte[] a1,byte[] a2){
  if (a1 == null || a2 == null) {
    return (a1 == a2);
  }
  if (a1.length != a2.length) {
    return false;
  }
  byte result=0;
  for (int i=0; i < a1.length; i++) {
    result|=a1[i] ^ a2[i];
  }
  return (result == 0);
}
