public final boolean member(Value elem){
  try {
    if (elem instanceof IntValue) {
      int x=((IntValue)elem).val;
      return (x >= low) && (x <= high);
    }
    if ((this.low <= this.high) && (!(elem instanceof ModelValue) || (((ModelValue)elem).type != 0))) {
      Assert.fail("Attempted to check if the value:\n" + Values.ppr(elem.toString()) + "\nis in the integer interval " + Values.ppr(this.toString()));
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
