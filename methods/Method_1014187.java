private double FRAK(double x){
  double ret=x - (int)(x);
  if (ret < 0) {
    ret+=1;
  }
  return ret;
}
