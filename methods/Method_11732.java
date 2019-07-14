private void findCommonPrefix(){
  fPrefix=0;
  int end=Math.min(fExpected.length(),fActual.length());
  for (; fPrefix < end; fPrefix++) {
    if (fExpected.charAt(fPrefix) != fActual.charAt(fPrefix)) {
      break;
    }
  }
}
