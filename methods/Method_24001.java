protected static int qualityToSamples(int quality){
  if (quality <= 1) {
    return 1;
  }
 else {
    int n=2 * (quality / 2);
    return n;
  }
}
