public final Value normalize(){
  try {
    if (this.realSet != null && this.realSet != SetEnumValue.DummyEnum) {
      this.realSet.normalize();
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
