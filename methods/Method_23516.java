public void setEmissive(int index,int emissive){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setEmissive()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setEmissive()");
    return;
  }
  vertices[index][PGraphics.ER]=((emissive >> 16) & 0xFF) / 255.0f;
  vertices[index][PGraphics.EG]=((emissive >> 8) & 0xFF) / 255.0f;
  vertices[index][PGraphics.EB]=((emissive >> 0) & 0xFF) / 255.0f;
}
