public final Value select(Value arg){
  try {
    if (this.intv != null) {
      if (!(arg instanceof IntValue)) {
        Assert.fail("Attempted to apply function with integer domain to" + " the non-integer argument " + Values.ppr(arg.toString()));
      }
      int idx=((IntValue)arg).val;
      if ((idx >= this.intv.low) && (idx <= this.intv.high)) {
        return this.values[idx - this.intv.low];
      }
    }
 else {
      if (this.indexTbl != null) {
        return this.values[this.lookupIndex(arg)];
      }
      if (this.isNorm)       this.createIndex();
      if (this.indexTbl != null) {
        return this.values[this.lookupIndex(arg)];
      }
 else {
        int len=this.domain.length;
        for (int i=0; i < len; i++) {
          if (this.domain[i].equals(arg)) {
            return this.values[i];
          }
        }
      }
    }
    return null;
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
