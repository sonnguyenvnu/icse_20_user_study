public void upath(final double x,final double y,UPath path){
  double lx=x;
  double ly=y;
  for (  USegment seg : path) {
    final USegmentType type=seg.getSegmentType();
    final double coord[]=seg.getCoord();
    if (type == USegmentType.SEG_MOVETO) {
      lx=coord[0] + x;
      ly=coord[1] + y;
    }
 else     if (type == USegmentType.SEG_LINETO) {
      line(lx,ly,coord[0] + x,coord[1] + y);
      lx=coord[0] + x;
      ly=coord[1] + y;
    }
 else     if (type == USegmentType.SEG_QUADTO) {
      line(lx,ly,coord[2] + x,coord[3] + y);
      lx=coord[2] + x;
      ly=coord[3] + y;
    }
 else     if (type == USegmentType.SEG_CUBICTO) {
      line(lx,ly,coord[4] + x,coord[5] + y);
      lx=coord[4] + x;
      ly=coord[5] + y;
    }
 else     if (type == USegmentType.SEG_CLOSE) {
    }
 else {
      Log.println("unknown5 " + seg);
    }
  }
}
