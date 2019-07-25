public final ValueEnumeration elements(){
  try {
    if (this.fcnSet == null || this.fcnSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.fcnSet.elements();
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
