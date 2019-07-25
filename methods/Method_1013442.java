public final int size(){
  try {
    if (this.fcnRcd == null) {
      return this.params.size();
    }
    return this.fcnRcd.size();
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
