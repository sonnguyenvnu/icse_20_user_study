/** 
 * @webref pshape:method
 * @brief Returns the total number of vertices as an int
 * @see PShape#getVertex(int)
 * @see PShape#setVertex(int,float,float)
 */
public int getVertexCount(){
  if (family == GROUP || family == PRIMITIVE) {
    PGraphics.showWarning(NO_VERTICES_ERROR);
  }
  return vertexCount;
}
