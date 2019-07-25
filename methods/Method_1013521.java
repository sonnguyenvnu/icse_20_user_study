public final ValueEnumeration elements(){
  try {
    if (this.diffSet == null || this.diffSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.diffSet.elements();
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
