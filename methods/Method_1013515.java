public final ValueEnumeration elements(){
  try {
    if (this.cupSet == null || this.cupSet == SetEnumValue.DummyEnum) {
      return new Enumerator();
    }
    return this.cupSet.elements();
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
