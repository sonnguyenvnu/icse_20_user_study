public int getStroke(int index){
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getStroke()");
    return strokeColor;
  }
  int a=(int)(vertices[index][PGraphics.SA] * 255);
  int r=(int)(vertices[index][PGraphics.SR] * 255);
  int g=(int)(vertices[index][PGraphics.SG] * 255);
  int b=(int)(vertices[index][PGraphics.SB] * 255);
  return (a << 24) | (r << 16) | (g << 8) | b;
}
