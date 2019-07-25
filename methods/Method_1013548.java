public final ValueEnumeration elements(){
  try {
    if (this.tool == null) {
      return ((SetEnumValue)this.inVal).elements();
    }
    return new Enumerator();
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
