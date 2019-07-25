public final boolean assignable(Value val){
  try {
    boolean canAssign=((val instanceof FcnRcdValue) && this.values.length == ((FcnRcdValue)val).values.length);
    if (!canAssign)     return false;
    FcnRcdValue fcn=(FcnRcdValue)val;
    for (int i=0; i < this.values.length; i++) {
      canAssign=(canAssign && this.domain[i].equals(fcn.domain[i]) && this.values[i].assignable(fcn.values[i]));
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
