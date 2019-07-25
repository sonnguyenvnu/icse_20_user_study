private void vline(double x,double y1,double y2){
  final int i=toInt(x);
  final int j1=toInt(y1);
  final int j2=toInt(y2);
  for (int j=j1; j <= j2; j++) {
    setState(i,j,true);
  }
}
