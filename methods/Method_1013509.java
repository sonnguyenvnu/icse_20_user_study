public final ValueEnumeration elements(){
  try {
    if (this.capSet == null || this.capSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.capSet.elements();
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
