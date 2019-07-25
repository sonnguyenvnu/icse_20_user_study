public final boolean assign(Value[] args,Value val){
  try {
    if (this.intv != null) {
      if (args.length != 1) {
        Assert.fail("Wrong number of function arguments.");
      }
      if (args[0] instanceof IntValue) {
        int idx=((IntValue)args[0]).val;
        if ((idx >= this.intv.low) && (idx <= this.intv.high)) {
          int vIdx=idx - this.intv.low;
          if (this.values[vIdx] == UndefValue.ValUndef || this.values[vIdx].equals(val)) {
            this.values[vIdx]=val;
            return true;
          }
          return false;
        }
      }
    }
 else {
      Value argv=new TupleValue(args);
      int len=this.domain.length;
      for (int i=0; i < len; i++) {
        if (this.domain[i].equals(argv)) {
          if (this.values[i] == UndefValue.ValUndef || this.values[i].equals(val)) {
            this.values[i]=val;
            return true;
          }
          return false;
        }
      }
    }
    Assert.fail("Function initialization out of domain.");
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
