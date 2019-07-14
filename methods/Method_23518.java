public void setShininess(int index,float shine){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setShininess()");
    return;
  }
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","setShininess()");
    return;
  }
  vertices[index][PGraphics.SHINE]=shine;
}
