public final Value normalize(){
  try {
    if (!this.isNorm) {
      this.elems.sort(true);
      this.isNorm=true;
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
