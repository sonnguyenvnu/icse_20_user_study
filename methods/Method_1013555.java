public final Value normalize(){
  try {
    if (this.pset == null || this.pset == SetEnumValue.DummyEnum) {
      this.set.normalize();
    }
 else {
      this.pset.normalize();
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
