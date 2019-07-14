public static boolean fomerIsBigger(int[] fomer,int[] current){
  if (fomer == null || current == null) {
    Log.e(TAG,"fomerIsBigger  fomer == null || current == null" + " >>  return false;");
    return false;
  }
  int compareLength=fomer.length < current.length ? fomer.length : current.length;
  for (int i=0; i < compareLength; i++) {
    if (fomer[i] < current[i]) {
      return false;
    }
    if (fomer[i] > current[i]) {
      return true;
    }
  }
  return false;
}
