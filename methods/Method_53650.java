double getReverseSawValue(double time){
  return (1 - ((getP(time) * 2 + 1) % 2));
}
