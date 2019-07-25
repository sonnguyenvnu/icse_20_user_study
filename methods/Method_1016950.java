public double[] sort(){
  sortx=(double[])x.clone();
  int incr=(int)(n * .5);
  while (incr >= 1) {
    for (int i=incr; i < n; i++) {
      double temp=sortx[i];
      int j=i;
      while (j >= incr && temp < sortx[j - incr]) {
        sortx[j]=sortx[j - incr];
        j-=incr;
      }
      sortx[j]=temp;
    }
    incr/=2;
  }
  isSorted=true;
  return (sortx);
}
