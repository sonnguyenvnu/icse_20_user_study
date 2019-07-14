protected void post(PGraphics g){
  if (matrix != null) {
    g.popMatrix();
  }
  if (style) {
    g.popStyle();
  }
}
