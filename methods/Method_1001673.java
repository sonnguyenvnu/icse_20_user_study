private void simple(final Random rnd,final List<Point2D> points,double bubbleSize,final Point2D.Double pointA,final Point2D.Double pointB,final Point2D.Double pointC,final Point2D.Double pointD){
  specialLine(bubbleSize,rnd,points,pointA,pointB);
  specialLine(bubbleSize,rnd,points,pointB,pointC);
  specialLine(bubbleSize,rnd,points,pointC,pointD);
  specialLine(bubbleSize,rnd,points,pointD,pointA);
}
