public final int size(){
  try {
    long sz=1;
    for (int i=0; i < this.values.length; i++) {
      sz*=this.values[i].size();
      if (sz < -2147483648 || sz > 2147483647) {
        Assert.fail(EC.TLC_MODULE_OVERFLOW,"the number of elements in:\n" + Values.ppr(this.toString()));
      }
    }
    return (int)sz;
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
