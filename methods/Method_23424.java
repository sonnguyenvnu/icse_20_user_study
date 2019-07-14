/** 
 * Unavailable in 2D.
 * @throws IllegalArgumentException
 */
public void translate(float x,float y,float z){
  throw new IllegalArgumentException("Cannot use translate(x, y, z) on a PMatrix2D.");
}
