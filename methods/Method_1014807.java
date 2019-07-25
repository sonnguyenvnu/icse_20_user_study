/** 
 * Postmultiplies this matrix by a translation matrix. Postmultiplication is also used by OpenGL ES' 1.x glTranslate/glRotate/glScale.
 * @param x The x-component of the translation vector.
 * @param y The y-component of the translation vector.
 * @return This matrix for the purpose of chaining. 
 */
public Matrix3 translate(float x,float y){
  float[] val=this.val;
  tmp[M00]=1;
  tmp[M10]=0;
  tmp[M20]=0;
  tmp[M01]=0;
  tmp[M11]=1;
  tmp[M21]=0;
  tmp[M02]=x;
  tmp[M12]=y;
  tmp[M22]=1;
  mul(val,tmp);
  return this;
}
