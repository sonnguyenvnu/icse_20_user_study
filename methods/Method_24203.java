@Override public void resetShader(int kind){
  flush();
  if (kind == TRIANGLES || kind == QUADS || kind == POLYGON) {
    polyShader=null;
  }
 else   if (kind == LINES) {
    lineShader=null;
  }
 else   if (kind == POINTS) {
    pointShader=null;
  }
 else {
    PGraphics.showWarning(UNKNOWN_SHADER_KIND_ERROR);
  }
}
