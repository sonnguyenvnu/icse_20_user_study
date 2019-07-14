@Override protected void setParent(PShapeSVG parent){
  super.setParent(parent);
  if (parent instanceof PShapeJava2D) {
    PShapeJava2D pj=(PShapeJava2D)parent;
    fillGradientPaint=pj.fillGradientPaint;
    strokeGradientPaint=pj.strokeGradientPaint;
  }
 else {
    fillGradientPaint=null;
    strokeGradientPaint=null;
  }
}
