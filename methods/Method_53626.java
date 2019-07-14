public void addGraph(int n,float[] x,float[] y,Color c,int mode){
  if (xPoints.length <= n) {
    float[][] yp=new float[n + 1][];
    float[][] xp=new float[n + 1][];
    Color[] ncol=new Color[n + 1];
    int[] m=new int[n + 1];
    for (int i=0; i < xPoints.length; i++) {
      xp[i]=xPoints[i];
      yp[i]=yPoints[i];
      ncol[i]=pointColor[i];
      m[i]=pointMode[i];
    }
    pointColor=ncol;
    xPoints=xp;
    yPoints=yp;
    pointMode=m;
  }
  xPoints[n]=x;
  yPoints[n]=y;
  pointColor[n]=c;
  pointMode[n]=mode;
  calcRange();
  repaint();
}
