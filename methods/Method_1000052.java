private boolean check(byte[] stats){
  if (stats == null || stats.length == 0) {
    return false;
  }
  for (int i=0; i < stats.length; i++) {
    if (check[i] != stats[i]) {
      return false;
    }
  }
  return true;
}
