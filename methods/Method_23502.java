public int getFill(int index){
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getFill()");
    return fillColor;
  }
  if (image == null) {
    int a=(int)(vertices[index][PGraphics.A] * 255);
    int r=(int)(vertices[index][PGraphics.R] * 255);
    int g=(int)(vertices[index][PGraphics.G] * 255);
    int b=(int)(vertices[index][PGraphics.B] * 255);
    return (a << 24) | (r << 16) | (g << 8) | b;
  }
 else {
    return 0;
  }
}
