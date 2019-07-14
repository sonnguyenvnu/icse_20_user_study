/** 
 * @param z the z value for the vertex
 */
public void setVertex(int index,float x,float y,float z){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setVertex()");
    return;
  }
  vertices[index][X]=x;
  vertices[index][Y]=y;
  vertices[index][Z]=z;
}
