public final IValue permute(IMVPerm perm){
  try {
    this.normalize();
    int rlen=this.names.length;
    Value[] vals=new Value[rlen];
    boolean changed=false;
    for (int i=0; i < rlen; i++) {
      vals[i]=(Value)this.values[i].permute(perm);
      changed=changed || (vals[i] != this.values[i]);
    }
    if (changed) {
      return new RecordValue(this.names,vals,true);
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
