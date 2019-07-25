public final boolean member(Value elem){
  try {
    TupleValue tv=(TupleValue)elem.toTuple();
    if (tv == null) {
      FcnRcdValue fcn=(FcnRcdValue)elem.toFcnRcd();
      if (fcn == null) {
        if (elem instanceof ModelValue)         return ((ModelValue)elem).modelValueMember(this);
        Assert.fail("Attempted to check if non-tuple\n" + Values.ppr(elem.toString()) + "\nis in the set of tuples:\n" + Values.ppr(this.toString()));
      }
      if (fcn.intv != null)       return false;
      for (int i=0; i < fcn.domain.length; i++) {
        if (!(fcn.domain[i] instanceof IntValue)) {
          Assert.fail("Attempted to check if non-tuple\n" + Values.ppr(elem.toString()) + "\nis in the set of tuples:\n" + Values.ppr(this.toString()));
        }
      }
      return false;
    }
    if (tv.elems.length == this.sets.length) {
      for (int i=0; i < this.sets.length; i++) {
        if (!this.sets[i].member(tv.elems[i]))         return false;
      }
      return true;
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
