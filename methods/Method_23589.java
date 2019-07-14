public int minIndex(){
  checkMinMax("minIndex");
  double m=Double.NaN;
  int mi=-1;
  for (int i=0; i < count; i++) {
    if (data[i] == data[i]) {
      m=data[i];
      mi=i;
      for (int j=i + 1; j < count; j++) {
        double d=data[j];
        if (!Double.isNaN(d) && (d < m)) {
          m=data[j];
          mi=j;
        }
      }
      break;
    }
  }
  return mi;
}
