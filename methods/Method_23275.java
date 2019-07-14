static final public float[] parseFloat(byte what[]){
  float floaties[]=new float[what.length];
  for (int i=0; i < what.length; i++) {
    floaties[i]=what[i];
  }
  return floaties;
}
