/** 
 * Same as  {@code Matrix.isIdentity()}, but with tolerance  {@code eps}.
 */
private boolean isMatrixIdentity(Matrix transform,float eps){
  transform.getValues(mTempValues);
  mTempValues[0]-=1.0f;
  mTempValues[4]-=1.0f;
  mTempValues[8]-=1.0f;
  for (int i=0; i < 9; i++) {
    if (Math.abs(mTempValues[i]) > eps) {
      return false;
    }
  }
  return true;
}
