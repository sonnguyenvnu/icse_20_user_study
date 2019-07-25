public final boolean member(Value elem){
  try {
    return this.set1.member(elem) || this.set2.member(elem);
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
