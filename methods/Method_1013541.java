public final Value normalize(){
  try {
    if (this.tupleSet == null || this.tupleSet == SetEnumValue.DummyEnum) {
      for (int i=0; i < this.sets.length; i++) {
        this.sets[i].normalize();
      }
    }
 else {
      this.tupleSet.normalize();
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
