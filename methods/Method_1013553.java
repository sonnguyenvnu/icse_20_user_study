public final boolean member(Value val){
  try {
    if (val instanceof Enumerable) {
      ValueEnumeration Enum=((Enumerable)val).elements();
      Value elem;
      while ((elem=Enum.nextElement()) != null) {
        if (!this.set.member(elem)) {
          return false;
        }
      }
    }
 else {
      Assert.fail("Attempted to check if the non-enumerable value\n" + Values.ppr(val.toString()) + "\nis element of\n" + Values.ppr(this.toString()));
    }
    return true;
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
