/** 
 * @nowebref
 */
public void setStroke(int index,int stroke){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setStroke()");
    return;
  }
  if (!perVertexStyles) {
    PGraphics.showWarning(PER_VERTEX_UNSUPPORTED,"setStroke()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setStroke()");
    return;
  }
  vertices[index][PGraphics.SA]=((stroke >> 24) & 0xFF) / 255.0f;
  vertices[index][PGraphics.SR]=((stroke >> 16) & 0xFF) / 255.0f;
  vertices[index][PGraphics.SG]=((stroke >> 8) & 0xFF) / 255.0f;
  vertices[index][PGraphics.SB]=((stroke >> 0) & 0xFF) / 255.0f;
}
