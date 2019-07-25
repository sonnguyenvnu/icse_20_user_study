static void shorten(CubicCurve2D.Double bez,Shape shape){
  final boolean contains1=shape.contains(bez.x1,bez.y1);
  final boolean contains2=shape.contains(bez.x2,bez.y2);
  if (contains1 ^ contains2 == false) {
    throw new IllegalArgumentException();
  }
  if (contains1 == false) {
    bez.setCurve(bez.x2,bez.y2,bez.ctrlx2,bez.ctrly2,bez.ctrlx1,bez.ctrly1,bez.x1,bez.y1);
  }
  assert shape.contains(bez.x1,bez.y1) && shape.contains(bez.x2,bez.y2) == false;
  final CubicCurve2D.Double left=new CubicCurve2D.Double();
  final CubicCurve2D.Double right=new CubicCurve2D.Double();
  subdivide(bez,left,right,0.5);
  if (isCutting(left,shape) ^ isCutting(right,shape) == false) {
    throw new IllegalArgumentException();
  }
  if (isCutting(left,shape)) {
    bez.setCurve(left);
  }
 else {
    bez.setCurve(right);
  }
}
