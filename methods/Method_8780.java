private void smoothenAndPaintPoints(boolean ended){
  if (pointsCount > 2) {
    Vector<Point> points=new Vector<>();
    Point prev2=this.points[0];
    Point prev1=this.points[1];
    Point cur=this.points[2];
    if (cur == null || prev1 == null || prev2 == null) {
      return;
    }
    Point midPoint1=prev1.multiplySum(prev2,0.5f);
    Point midPoint2=cur.multiplySum(prev1,0.5f);
    int segmentDistance=1;
    float distance=midPoint1.getDistanceTo(midPoint2);
    int numberOfSegments=(int)Math.min(48,Math.max(Math.floor(distance / segmentDistance),24));
    float t=0.0f;
    float step=1.0f / (float)numberOfSegments;
    for (int j=0; j < numberOfSegments; j++) {
      Point point=smoothPoint(midPoint1,midPoint2,prev1,t);
      if (isFirst) {
        point.edge=true;
        isFirst=false;
      }
      points.add(point);
      t+=step;
    }
    if (ended) {
      midPoint2.edge=true;
    }
    points.add(midPoint2);
    Point[] result=new Point[points.size()];
    points.toArray(result);
    Path path=new Path(result);
    paintPath(path);
    System.arraycopy(this.points,1,this.points,0,2);
    if (ended) {
      pointsCount=0;
    }
 else {
      pointsCount=2;
    }
  }
 else {
    Point[] result=new Point[pointsCount];
    System.arraycopy(this.points,0,result,0,pointsCount);
    Path path=new Path(result);
    paintPath(path);
  }
}
