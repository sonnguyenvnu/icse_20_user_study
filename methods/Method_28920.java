public static int getSlot(String key){
  int s=key.indexOf("{");
  if (s > -1) {
    int e=key.indexOf("}",s + 1);
    if (e > -1 && e != s + 1) {
      key=key.substring(s + 1,e);
    }
  }
  return getCRC16(key) & (16384 - 1);
}
