public int getSpecular(int index){
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getSpecular()");
    return specularColor;
  }
  int r=(int)(vertices[index][PGraphics.SPR] * 255);
  int g=(int)(vertices[index][PGraphics.SPG] * 255);
  int b=(int)(vertices[index][PGraphics.SPB] * 255);
  return 0xff000000 | (r << 16) | (g << 8) | b;
}
