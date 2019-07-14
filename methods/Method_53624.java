public void addGraph(int n,double[] x,float[] y,Color c,int mode){
  float[] xf=new float[x.length];
  for (int i=0; i < x.length; i++) {
    xf[i]=(float)x[i];
  }
  addGraph(n,xf,y,c,mode);
  calcRange();
  repaint();
}
