public final Value normalize(){
  try {
    if (this.cupSet != null && this.cupSet != SetEnumValue.DummyEnum) {
      this.cupSet.normalize();
    }
    return this;
  }
 catch (  RuntimeException|OutOfMemoryError e) {
    if (hasSource()) {
      throw FingerprintException.getNewHead(this,e);
    }
 else {
      throw e;
    }
  }
}
