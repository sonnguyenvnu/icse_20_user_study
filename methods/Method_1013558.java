public final ValueEnumeration elements(){
  try {
    if (this.pset == null || this.pset == SetEnumValue.DummyEnum) {
      return elementsNormalized();
    }
    return this.pset.elements();
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
