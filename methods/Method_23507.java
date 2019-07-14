public void setTint(int index,int tint){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTint()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setTint()");
    return;
  }
  if (image != null) {
    vertices[index][PGraphics.A]=((tint >> 24) & 0xFF) / 255.0f;
    vertices[index][PGraphics.R]=((tint >> 16) & 0xFF) / 255.0f;
    vertices[index][PGraphics.G]=((tint >> 8) & 0xFF) / 255.0f;
    vertices[index][PGraphics.B]=((tint >> 0) & 0xFF) / 255.0f;
  }
}
