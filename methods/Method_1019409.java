@Override public void receive(Collection<Long> activity){
  curveIsEmpty=true;
  float scale=1.0f / RESOLUTION_MS;
  for (  long t : activity) {
    long age=now - t;
    if (age < 0) {
      continue;
    }
    float e=age * scale;
    float sigma=(float)Math.sqrt(e + DATA_STROKE_WIDTH);
    float support=2.7f * sigma;
    int left=Math.max(0,(int)(e - support));
    if (left > range) {
      continue;
    }
    if (curveIsEmpty) {
      curveIsEmpty=false;
      Arrays.fill(curve,0.0f);
    }
    int right=Math.min(range,(int)(e + support));
    float inverseSigma=1.0f / sigma;
    for (int i=left; i < right; ++i) {
      curve[i]+=gaussian(e,inverseSigma,i);
    }
  }
}
