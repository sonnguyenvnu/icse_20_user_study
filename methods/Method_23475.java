protected void pre(PGraphics g){
  if (matrix != null) {
    g.pushMatrix();
    g.applyMatrix(matrix);
  }
  if (style) {
    g.pushStyle();
    styles(g);
  }
}
