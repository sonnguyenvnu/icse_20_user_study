public final boolean assignable(Value val){
  try {
    boolean canAssign=((val instanceof TupleValue) && (this.elems.length == ((TupleValue)val).elems.length));
    if (!canAssign)     return false;
    for (int i=0; i < this.elems.length; i++) {
      canAssign=canAssign && this.elems[i].assignable(((TupleValue)val).elems[i]);
    }
    return canAssign;
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
