public double exp(){
  double exp=Math.exp(logVal);
  if (!sign) {
    exp*=-1;
  }
  return exp;
}
