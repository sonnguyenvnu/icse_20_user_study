public final int size(){
  try {
    if (this.high < this.low)     return 0;
    return this.high - this.low + 1;
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
