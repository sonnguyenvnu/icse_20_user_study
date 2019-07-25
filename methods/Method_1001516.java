private void hline(double y,double x1,double x2){
  final int j=toInt(y);
  final int i1=toInt(x1);
  final int i2=toInt(x2);
  for (int i=i1; i <= i2; i++) {
    setState(i,j,true);
  }
}
