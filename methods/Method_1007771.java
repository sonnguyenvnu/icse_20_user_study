private double evaluate(double arc,double leftArc,double leftParameter,double rightArc,double rightParameter){
  double midParameter=0;
  for (int i=0; i < 10; ++i) {
    midParameter=(leftParameter + rightParameter) * 0.5;
    final double midArc=baseInterpolation.arcLength(0,midParameter);
    cache.put(midArc,midParameter);
    if (midArc < leftArc) {
      return leftParameter;
    }
    if (midArc > rightArc) {
      return rightParameter;
    }
    if (Math.abs(midArc - arc) < 0.01) {
      return midParameter;
    }
    if (arc < midArc) {
      rightArc=midArc;
      rightParameter=midParameter;
    }
 else {
      leftArc=midArc;
      leftParameter=midParameter;
    }
  }
  return midParameter;
}
