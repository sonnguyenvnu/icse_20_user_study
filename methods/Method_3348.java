@Override protected void toLog(){
  super.toLog();
  if (transition_probability2 != null) {
    for (    float[][] m : transition_probability2) {
      for (      float[] v : m) {
        for (int i=0; i < v.length; i++) {
          v[i]=(float)Math.log(v[i]);
        }
      }
    }
  }
}
