/** 
 * @webref pshape:method
 * @brief Sets the vertex at the index position
 * @param index the location of the vertex
 * @param x the x value for the vertex
 * @param y the y value for the vertex
 * @see PShape#getVertex(int)
 * @see PShape#getVertexCount()
 */
public void setVertex(int index,float x,float y){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setVertex()");
    return;
  }
  vertices[index][X]=x;
  vertices[index][Y]=y;
}
