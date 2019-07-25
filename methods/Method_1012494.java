double posmod(double x,double y){
  x%=y;
  return (x >= 0) ? x : x + y;
}
