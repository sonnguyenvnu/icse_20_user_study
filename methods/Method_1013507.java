public final Value normalize(){
  try {
    if (this.capSet == null || this.capSet == SetEnumValue.DummyEnum) {
      this.set1.normalize();
      this.set2.normalize();
    }
 else {
      this.capSet.normalize();
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
