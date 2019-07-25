public final ValueEnumeration elements(){
  try {
    if (this.realSet == null || this.realSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.realSet.elements();
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
