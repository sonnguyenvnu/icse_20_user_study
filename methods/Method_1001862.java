public List<Point2D.Double> next(){
  if (pos == -1) {
    return Collections.emptyList();
  }
  try {
    final List<Point2D.Double> result=svg.substring(pos).extractList(SvgResult.POINTS_EQUALS);
    pos=svg.indexOf(SvgResult.POINTS_EQUALS,pos) + SvgResult.POINTS_EQUALS.length() + 1;
    return result;
  }
 catch (  StringIndexOutOfBoundsException e) {
    Log.error("Error " + e);
    return Collections.emptyList();
  }
}
