@Override public void getTransform(Matrix transform){
  getParentTransform(transform);
  if (!mRotationMatrix.isIdentity()) {
    transform.preConcat(mRotationMatrix);
  }
}
