public double getIndicator(){
  double skew=getSkew();
  return (getHint() * (skew < 1 ? 1 - Math.pow(skew,3) : 0)) / 15.0;
}
