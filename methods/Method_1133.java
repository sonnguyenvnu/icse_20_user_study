/** 
 * TransformationCallback method
 * @param transform
 */
@Override public void getTransform(Matrix transform){
  super.getTransform(transform);
  if (mDrawMatrix != null) {
    transform.preConcat(mDrawMatrix);
  }
}
