public void setSpecular(int index,int specular){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setSpecular()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setSpecular()");
    return;
  }
  vertices[index][PGraphics.SPR]=((specular >> 16) & 0xFF) / 255.0f;
  vertices[index][PGraphics.SPG]=((specular >> 8) & 0xFF) / 255.0f;
  vertices[index][PGraphics.SPB]=((specular >> 0) & 0xFF) / 255.0f;
}
