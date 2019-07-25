public final boolean member(Value elem){
  try {
    Assert.fail("Attempted to check if the value:\n" + elem == null ? "null" : Values.ppr(elem.toString()) + "\nis an element of operator " + this.toString());
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
