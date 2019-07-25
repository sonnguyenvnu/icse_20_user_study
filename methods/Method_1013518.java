public final Value normalize(){
  try {
    if (this.diffSet == null || this.diffSet == SetEnumValue.DummyEnum) {
      this.set1.normalize();
      this.set2.normalize();
    }
 else {
      this.diffSet.normalize();
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
