public final int size(){
  try {
    int sz=this.set.size();
    if (sz >= 31) {
      Assert.fail(EC.TLC_MODULE_OVERFLOW,"the number of elements in:\n" + Values.ppr(this.toString()));
    }
    return (1 << sz);
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
