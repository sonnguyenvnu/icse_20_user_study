public HectorPath uncompress(LineSegmentDouble segment){
  double x1=segment.getX1();
  double y1=segment.getY1();
  final double x2=segment.getX2();
  final double y2=segment.getY2();
  final HectorPath result=new HectorPath();
  final double x[]=compX.encounteredSingularities(x1,x2);
  if (x.length == 0) {
    result.add(getUncompressedSegment(x1,y1,x2,y2,UnlinearCompression.Rounding.BORDER_2));
    return result;
  }
  for (int i=0; i < x.length; i++) {
    final double y=segment.getIntersectionVertical(x[i]);
    result.add(getUncompressedSegment(x1,y1,x[i],y,UnlinearCompression.Rounding.BORDER_2));
    x1=x[i];
    y1=y;
  }
  result.add(getUncompressedSegment(x1,y1,x2,y2,UnlinearCompression.Rounding.BORDER_2));
  return result;
}
