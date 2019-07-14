public void addRange(int n,float[] x,float[] y1,float[] y2,Color c){
  if (xPoints.length <= n) {
    float[][] yp=new float[n + 2][];
    float[][] xp=new float[n + 2][];
    Color[] ncol=new Color[n + 2];
    int[] m=new int[n + 2];
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
  yPoints[n]=y1;
  pointColor[n]=c;
  pointMode[n]=-1;
  xPoints[n + 1]=x;
  yPoints[n + 1]=y2;
  pointColor[n + 1]=c;
  pointMode[n + 1]=RANGE_MODE;
  calcRange();
  repaint();
}
