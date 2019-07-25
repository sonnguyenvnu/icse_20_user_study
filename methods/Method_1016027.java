public final static int adapt(int delta,final int numpoints,final boolean first){
  if (first) {
    delta=delta / DAMP;
  }
 else {
    delta=delta / 2;
  }
  delta=delta + (delta / numpoints);
  int k=0;
  while (delta > ((BASE - TMIN) * TMAX) / 2) {
    delta=delta / (BASE - TMIN);
    k=k + BASE;
  }
  return k + ((BASE - TMIN + 1) * delta) / (delta + SKEW);
}
