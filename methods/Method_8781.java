private Point smoothPoint(Point midPoint1,Point midPoint2,Point prev1,float t){
  double a1=Math.pow(1.0f - t,2);
  double a2=(2.0f * (1.0f - t) * t);
  double a3=t * t;
  return new Point(midPoint1.x * a1 + prev1.x * a2 + midPoint2.x * a3,midPoint1.y * a1 + prev1.y * a2 + midPoint2.y * a3,1.0f);
}
