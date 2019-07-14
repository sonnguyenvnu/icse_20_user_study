void waveGen(int base){
  float fb=base / 100f;
  float sc=((base) % 10000) / 10000f + 1;
  float[] xPoints=new float[1024];
  float[] yPoints=new float[xPoints.length];
  for (int i=0; i < xPoints.length; i++) {
    float x=(float)((i + fb) * 10 * Math.PI / xPoints.length);
    xPoints[i]=x;
    yPoints[i]=(float)Math.sin(x) * sc;
  }
  addGraph(0,xPoints,yPoints,Color.WHITE,0);
}
