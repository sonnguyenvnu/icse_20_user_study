/** 
 * @param vec the PVector to define the x, y, z coordinates
 */
public void setVertex(int index,PVector vec){
  if (openShape) {
    PGraphics.showWarning(INSIDE_BEGIN_END_ERROR,"setVertex()");
    return;
  }
  vertices[index][X]=vec.x;
  vertices[index][Y]=vec.y;
  if (vertices[index].length > 2) {
    vertices[index][Z]=vec.z;
  }
 else   if (vec.z != 0 && vec.z == vec.z) {
    throw new IllegalArgumentException("Cannot set a z-coordinate on a 2D shape");
  }
}
