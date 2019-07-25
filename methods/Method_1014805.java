/** 
 * Adds a translational component to the matrix in the 3rd column. The other columns are untouched.
 * @param x The x-component of the translation vector.
 * @param y The y-component of the translation vector.
 * @return This matrix for the purpose of chaining. 
 */
public Matrix3 trn(float x,float y){
  val[M02]+=x;
  val[M12]+=y;
  return this;
}
