public void setCycle(int n,double[] pos,double[] period,double[] amplitude,double[] offset,int selected,int curveType){
  double[] t=new double[pos.length];
  double[][] v=new double[pos.length][2];
  for (int i=0; i < pos.length; i++) {
    t[i]=pos[i];
    v[i][0]=amplitude[i];
    v[i][1]=offset[i];
  }
  MonotoneSpline ms=new MonotoneSpline(t,v);
  Oscillator osc=new Oscillator();
  osc.mType=curveType;
  for (int i=0; i < pos.length; i++) {
    osc.addPoint(pos[i],(float)period[i]);
  }
  osc.normalize();
  mMonotoneSpline=ms;
  mOscillator=osc;
  float[] x=new float[400];
  double[] y1=new double[x.length];
  float[] yMax=new float[x.length];
  float[] yMin=new float[x.length];
  for (int i=0; i < x.length; i++) {
    x[i]=(float)(i / (x.length - 1.0f));
    double amp=mMonotoneSpline.getPos(x[i],0);
    double off=mMonotoneSpline.getPos(x[i],1);
    y1[i]=mOscillator.getValue(x[i]) * amp + off;
    yMax[i]=(float)(amp + off);
    yMin[i]=(float)(-amp + off);
  }
  n=n * 4;
  addGraph(n,x,y1,Color.BLUE,0);
  addRange(n + 1,x,yMin,yMax,REGION_COLOR);
  addGraph(n + 3,pos,offset,Color.PINK,1);
  selected_node=selected;
  selected_graph=n + 3;
}
