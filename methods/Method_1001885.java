public void upath(double x,double y,UPath path){
  final StringBuilder sb=new StringBuilder();
  appendShadeOrDraw(sb);
  sb.append("line width=" + thickness + "pt");
  if (dash != null) {
    sb.append(",dash pattern=" + dash);
  }
  sb.append("] ");
  for (  USegment seg : path) {
    final USegmentType type=seg.getSegmentType();
    final double coord[]=seg.getCoord();
    if (type == USegmentType.SEG_MOVETO) {
      sb.append(couple(coord[0] + x,coord[1] + y));
    }
 else     if (type == USegmentType.SEG_LINETO) {
      sb.append(" -- ");
      sb.append(couple(coord[0] + x,coord[1] + y));
    }
 else     if (type == USegmentType.SEG_QUADTO) {
      throw new UnsupportedOperationException();
    }
 else     if (type == USegmentType.SEG_CUBICTO) {
      sb.append(" ..controls ");
      sb.append(couple(coord[0] + x,coord[1] + y));
      sb.append(" and ");
      sb.append(couple(coord[2] + x,coord[3] + y));
      sb.append(" .. ");
      sb.append(couple(coord[4] + x,coord[5] + y));
    }
 else     if (type == USegmentType.SEG_CLOSE) {
    }
 else {
      Log.println("unknown4 " + seg);
    }
  }
  sb.append(";");
  addCommand(sb);
}
