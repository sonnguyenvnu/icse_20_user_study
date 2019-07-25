/** 
 * Adds a translational component to the matrix in the 3rd column. The other columns are untouched.
 * @param vector The translation vector.
 * @return This matrix for the purpose of chaining. 
 */
public Matrix3 trn(Vector2 vector){
  val[M02]+=vector.x;
  val[M12]+=vector.y;
  return this;
}
