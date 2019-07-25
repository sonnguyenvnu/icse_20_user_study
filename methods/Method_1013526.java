public final IValue permute(IMVPerm perm){
  try {
    int sz=this.elems.size();
    Value[] vals=new Value[sz];
    boolean changed=false;
    for (int i=0; i < sz; i++) {
      vals[i]=(Value)this.elems.elementAt(i).permute(perm);
      changed=(changed || vals[i] != this.elems.elementAt(i));
    }
    if (changed) {
      return new SetEnumValue(vals,false);
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
