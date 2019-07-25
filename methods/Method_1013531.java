public final Value normalize(){
  try {
    if (this.fcnSet == null || this.fcnSet == SetEnumValue.DummyEnum) {
      this.domain.normalize();
      this.range.normalize();
    }
 else {
      this.fcnSet.normalize();
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
