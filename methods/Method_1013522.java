public final boolean member(Value elem){
  try {
    return this.elems.search(elem,this.isNorm);
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
