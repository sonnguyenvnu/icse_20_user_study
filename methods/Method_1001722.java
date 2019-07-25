static private boolean contains(CubicCurve2D.Double cubic,Rectangle2D... rects){
  for (  Rectangle2D r : rects) {
    if (r.contains(cubic.getP1()) && r.contains(cubic.getP2())) {
      return true;
    }
  }
  return false;
}
