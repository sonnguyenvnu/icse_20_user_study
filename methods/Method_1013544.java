public final int size(){
  try {
    this.inVal=this.toSetEnum();
    this.tool=null;
    return this.inVal.size();
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
