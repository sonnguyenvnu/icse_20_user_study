public final Value apply(Value[] args,int control){
  try {
    int sz=this.domain.size();
    for (int i=0; i < sz; i++) {
      Value[] vals=(Value[])this.domain.elementAt(i);
      if (args.length != vals.length) {
        Assert.fail("Attempted to apply the operator " + Values.ppr(this.toString()) + "\nwith wrong number of arguments.");
      }
      boolean matched=true;
      for (int j=0; j < vals.length; j++) {
        matched=vals[j].equals(args[j]);
        if (!matched)         break;
      }
      if (matched) {
        return (Value)this.values.elementAt(i);
      }
    }
    String msg="Attempted to apply operator:\n" + Values.ppr(this.toString()) + "\nto arguments (";
    if (args.length > 0)     msg+=args[0];
    for (int i=1; i < args.length; i++) {
      msg+=", " + args[i];
    }
    Assert.fail(msg + "), which is undefined.");
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
