public static CubicCurve2D.Double reverse(CubicCurve2D curv){
  return new CubicCurve2D.Double(curv.getX2(),curv.getY2(),curv.getCtrlX2(),curv.getCtrlY2(),curv.getCtrlX1(),curv.getCtrlY1(),curv.getX1(),curv.getY1());
}
