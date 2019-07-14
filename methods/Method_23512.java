public int getAmbient(int index){
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getAmbient()");
    return ambientColor;
  }
  int r=(int)(vertices[index][PGraphics.AR] * 255);
  int g=(int)(vertices[index][PGraphics.AG] * 255);
  int b=(int)(vertices[index][PGraphics.AB] * 255);
  return 0xff000000 | (r << 16) | (g << 8) | b;
}
