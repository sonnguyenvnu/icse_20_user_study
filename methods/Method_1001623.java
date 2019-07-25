public Point2DInt[] intersect(LineSegmentInt seg){
  if (seg.side(this) != 0) {
    return new Point2DInt[0];
  }
  final Point2DInt corners[]=getCorners();
  final LineSegmentInt seg1=new LineSegmentInt(corners[0],corners[1]);
  final LineSegmentInt seg2=new LineSegmentInt(corners[1],corners[2]);
  final LineSegmentInt seg3=new LineSegmentInt(corners[2],corners[3]);
  final LineSegmentInt seg4=new LineSegmentInt(corners[3],corners[0]);
  final Point2DInt i1=seg.getSegIntersection(seg1);
  Point2DInt i2=seg.getSegIntersection(seg2);
  Point2DInt i3=seg.getSegIntersection(seg3);
  Point2DInt i4=seg.getSegIntersection(seg4);
  if (i2 != null && i2.equals(i1)) {
    i2=null;
  }
  if (i3 != null && (i3.equals(i1) || i3.equals(i2))) {
    i3=null;
  }
  if (i4 != null && (i4.equals(i1) || i4.equals(i2) || i4.equals(i3))) {
    i4=null;
  }
  final int nb=countNotNull(i1,i2,i3,i4);
  assert nb >= 0 && nb <= 3 : nb;
  int i=0;
  final Point2DInt result[]=new Point2DInt[nb];
  if (i1 != null) {
    result[i++]=i1;
  }
  if (i2 != null) {
    result[i++]=i2;
  }
  if (i3 != null) {
    result[i++]=i3;
  }
  if (i4 != null) {
    result[i++]=i4;
  }
  assert i == nb;
  assert getCornersOfOneSide(seg,0).length + getCornersOfOneSide(seg,1).length + getCornersOfOneSide(seg,-1).length == 4;
  return result;
}
