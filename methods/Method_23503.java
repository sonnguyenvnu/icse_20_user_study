/** 
 * @nowebref
 */
public void setFill(int index,int fill){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setFill()");
    return;
  }
  if (!perVertexStyles) {
    PGraphics.showWarning(PER_VERTEX_UNSUPPORTED,"setFill()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getFill()");
    return;
  }
  if (image == null) {
    vertices[index][PGraphics.A]=((fill >> 24) & 0xFF) / 255.0f;
    vertices[index][PGraphics.R]=((fill >> 16) & 0xFF) / 255.0f;
    vertices[index][PGraphics.G]=((fill >> 8) & 0xFF) / 255.0f;
    vertices[index][PGraphics.B]=((fill >> 0) & 0xFF) / 255.0f;
  }
}
