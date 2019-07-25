public final ValueEnumeration elements(){
  try {
    if (this.rcdSet == null || this.rcdSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.rcdSet.elements();
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
