public final boolean member(Value elem){
  try {
    if (!(this.set instanceof Enumerable)) {
      Assert.fail("Attempted to check if:\n " + Values.ppr(elem.toString()) + "\nis an element of the non-enumerable set:\n " + Values.ppr(this.toString()));
    }
    ValueEnumeration Enum=((Enumerable)this.set).elements();
    Value val;
    while ((val=Enum.nextElement()) != null) {
      if (val.member(elem))       return true;
    }
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
