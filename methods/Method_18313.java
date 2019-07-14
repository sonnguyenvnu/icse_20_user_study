void assertNotInMeasure(){
  if (mIsMeasuring) {
    throw new RuntimeException("Cannot update ComponentTree while in the middle of measure");
  }
}
