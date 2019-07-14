public float getShininess(int index){
  if (vertices == null || index >= vertices.length) {
    PGraphics.showWarning(NO_SUCH_VERTEX_ERROR + " (" + index + ")","getShininess()");
    return shininess;
  }
  return vertices[index][PGraphics.SHINE];
}
