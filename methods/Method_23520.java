/** 
 * Return true if this x, y coordinate is part of this shape. Only works with PATH shapes or GROUP shapes that contain other GROUPs or PATHs.
 */
public boolean contains(float x,float y){
  if (family == PATH) {
    PMatrix inverseCoords=matrix.get();
    inverseCoords.invert();
    inverseCoords.invert();
    PVector p=new PVector();
    inverseCoords.mult(new PVector(x,y),p);
    boolean c=false;
    for (int i=0, j=vertexCount - 1; i < vertexCount; j=i++) {
      if (((vertices[i][Y] > p.y) != (vertices[j][Y] > p.y)) && (p.x < (vertices[j][X] - vertices[i][X]) * (y - vertices[i][Y]) / (vertices[j][1] - vertices[i][Y]) + vertices[i][X])) {
        c=!c;
      }
    }
    return c;
  }
 else   if (family == GROUP) {
    for (int i=0; i < childCount; i++) {
      if (children[i].contains(x,y))       return true;
    }
    return false;
  }
 else {
    throw new IllegalArgumentException("The contains() method is only implemented for paths.");
  }
}
