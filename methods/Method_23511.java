public void setStrokeWeight(int index,float weight){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStrokeWeight()");
    return;
  }
  if (!perVertexStyles) {
    PGraphics.showWarning(PER_VERTEX_UNSUPPORTED,"setStrokeWeight()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setStrokeWeight()");
    return;
  }
  vertices[index][PGraphics.SW]=weight;
}
