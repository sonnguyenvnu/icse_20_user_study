/** 
 * Scale the matrix in the both the x and y components by the scalar value.
 * @param scale The single value that will be used to scale both the x and y components.
 * @return This matrix for the purpose of chaining methods together. 
 */
public Matrix3 scl(float scale){
  val[M00]*=scale;
  val[M11]*=scale;
  return this;
}
