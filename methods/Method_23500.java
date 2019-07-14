public void setTextureUV(int index,float u,float v){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setTextureUV()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setTextureUV()");
    return;
  }
  vertices[index][PGraphics.U]=u;
  vertices[index][PGraphics.V]=v;
}
