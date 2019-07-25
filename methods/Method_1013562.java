public final IValue permute(IMVPerm perm){
  try {
    Value[] vals=new Value[this.elems.length];
    boolean changed=false;
    for (int i=0; i < vals.length; i++) {
      vals[i]=(Value)this.elems[i].permute(perm);
      changed=changed || (vals[i] != this.elems[i]);
    }
    if (changed) {
      return new TupleValue(vals);
    }
    return this;
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
