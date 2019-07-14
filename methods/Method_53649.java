double getSawValue(double time){
  return ((getP(time) * 2 + 1) % 2) - 1;
}
