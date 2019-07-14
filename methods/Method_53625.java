public void addGraph(int n,double[][] p,Color c,int mode){
  float[] xf=new float[p.length];
  float[] yf=new float[p.length];
  for (int i=0; i < yf.length; i++) {
    xf[i]=(float)p[i][0];
    yf[i]=(float)p[i][1];
  }
  addGraph(n,xf,yf,c,mode);
  calcRange();
  repaint();
}
