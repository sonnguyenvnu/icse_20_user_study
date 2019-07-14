static public int smoothToSamples(int smooth){
  if (smooth == 0) {
    return 1;
  }
 else   if (smooth == 1) {
    return 2;
  }
 else {
    return smooth;
  }
}
