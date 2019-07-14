/** 
 * Returns the y-coordinate of the result of multiplying the vector (x, y, z, w) by this matrix.
 */
public float multY(float x,float y,float z,float w){
  return m10 * x + m11 * y + m12 * z + m13 * w;
}
