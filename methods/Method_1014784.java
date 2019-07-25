/** 
 * Postmultiplies this matrix by a translation matrix.
 * @param x The x-component of the translation vector.
 * @param y The y-component of the translation vector.
 * @return This matrix for the purpose of chaining. 
 */
public Affine2 translate(float x,float y){
  m02+=m00 * x + m01 * y;
  m12+=m10 * x + m11 * y;
  return this;
}
