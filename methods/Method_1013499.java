public final boolean member(Value elem){
  try {
    Assert.fail("Attempted to check if element:\n" + Values.ppr(elem.toString()) + "\nis in the record:\n" + Values.ppr(this.toString()));
    return false;
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
