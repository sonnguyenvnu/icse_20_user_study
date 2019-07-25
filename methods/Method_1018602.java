private int eval(int x){
  int y=coefficients[0];
  for (int i=1; i < coefficients.length; i++)   y=f.mul(y,x) ^ coefficients[i];
  return y;
}
