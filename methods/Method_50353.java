/** 
 * Like  {@link #getDisplayMatrix()}, but allows the user to provide a matrix to copy the values into to reduce object allocation
 * @param matrix target matrix to copy to
 */
@Override public void getDisplayMatrix(Matrix matrix){
  matrix.set(getDrawMatrix());
}
