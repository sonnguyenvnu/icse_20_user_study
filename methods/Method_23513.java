public void setAmbient(int index,int ambient){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setAmbient()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setAmbient()");
    return;
  }
  vertices[index][PGraphics.AR]=((ambient >> 16) & 0xFF) / 255.0f;
  vertices[index][PGraphics.AG]=((ambient >> 8) & 0xFF) / 255.0f;
  vertices[index][PGraphics.AB]=((ambient >> 0) & 0xFF) / 255.0f;
}
