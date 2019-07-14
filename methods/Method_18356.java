boolean isDirty(){
  assertMainThread();
  return mIsDirty;
}
