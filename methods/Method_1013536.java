public final Value normalize(){
  try {
    if (this.rcdSet == null || this.rcdSet == SetEnumValue.DummyEnum) {
      for (int i=0; i < this.names.length; i++) {
        this.values[i].normalize();
      }
    }
 else {
      this.rcdSet.normalize();
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
