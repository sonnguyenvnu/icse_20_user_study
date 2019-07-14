public void added(){
  if (mIsActivated && TangramBuilder.isPrintLog()) {
    throw new IllegalStateException("Component can not be added more than once");
  }
  mIsActivated=true;
  onAdded();
}
