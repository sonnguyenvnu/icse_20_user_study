public final boolean assignable(Value val){
  try {
    boolean canAssign=((val instanceof RecordValue) && this.names.length == ((RecordValue)val).names.length);
    if (!canAssign)     return false;
    for (int i=0; i < this.values.length; i++) {
      canAssign=(canAssign && this.names[i].equals(((RecordValue)val).names[i]) && this.values[i].assignable(((RecordValue)val).values[i]));
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
