/** 
 * Sets the matrix.
 * @param matrix matrix to set
 */
public void setMatrix(Matrix matrix){
  mMatrix=matrix;
  configureBounds();
  invalidateSelf();
}
