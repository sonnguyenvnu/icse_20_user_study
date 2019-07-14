/** 
 * TransformationCallback method
 * @param transform
 */
@Override public void getTransform(Matrix transform){
  getParentTransform(transform);
  configureBoundsIfUnderlyingChanged();
  if (mDrawMatrix != null) {
    transform.preConcat(mDrawMatrix);
  }
}
