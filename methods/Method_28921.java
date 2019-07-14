public static int getSlot(byte[] key){
  int s=-1;
  int e=-1;
  boolean sFound=false;
  for (int i=0; i < key.length; i++) {
    if (key[i] == '{' && !sFound) {
      s=i;
      sFound=true;
    }
    if (key[i] == '}' && sFound) {
      e=i;
      break;
    }
  }
  if (s > -1 && e > -1 && e != s + 1) {
    return getCRC16(key,s + 1,e) & (16384 - 1);
  }
  return getCRC16(key) & (16384 - 1);
}
