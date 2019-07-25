private void complex(final Random rnd,final List<Point2D> points,double bubbleSize,final Point2D.Double pointA,final Point2D.Double pointB,final Point2D.Double pointC,final Point2D.Double pointD){
  final double margin2=7;
  specialLine(bubbleSize,rnd,points,mvX(pointA,margin2),mvX(pointB,-margin2));
  points.add(mvY(pointB,margin2));
  specialLine(bubbleSize,rnd,points,mvY(pointB,margin2),mvY(pointC,-margin2));
  points.add(mvX(pointC,-margin2));
  specialLine(bubbleSize,rnd,points,mvX(pointC,-margin2),mvX(pointD,margin2));
  points.add(mvY(pointD,-margin2));
  specialLine(bubbleSize,rnd,points,mvY(pointD,-margin2),mvY(pointA,margin2));
  points.add(mvX(pointA,margin2));
}
