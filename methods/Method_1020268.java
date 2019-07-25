public void frustumxi(int left,int right,int bottom,int top,int near,int far){
  double r=right / 2048;
  double l=left / 2048;
  double t=top / 2048;
  double b=bottom / 2048;
  double n=near / 65536;
  double f=far / 65536;
  projection(projm,r - l,t - b,90,n,f);
}
