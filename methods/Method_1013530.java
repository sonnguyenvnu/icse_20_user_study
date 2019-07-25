public final int size(){
  try {
    int dsz=this.domain.size();
    int rsz=this.range.size();
    long sz=1;
    for (int i=0; i < dsz; i++) {
      sz*=rsz;
      if (sz < -2147483648 || sz > 2147483647) {
        Assert.fail("Overflow when computing the number of elements in:\n" + Values.ppr(toString()));
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
