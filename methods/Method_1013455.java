public final IValue permute(IMVPerm perm){
  try {
    this.normalize();
    int flen=this.domain.length;
    Value[] vals=new Value[flen];
    boolean vchanged=false;
    for (int i=0; i < flen; i++) {
      vals[i]=(Value)this.values[i].permute(perm);
      vchanged=vchanged || (vals[i] != this.values[i]);
    }
    if (this.intv == null) {
      Value[] dom=new Value[flen];
      boolean dchanged=false;
      for (int i=0; i < flen; i++) {
        dom[i]=(Value)this.domain[i].permute(perm);
        dchanged=dchanged || (dom[i] != this.domain[i]);
      }
      if (dchanged) {
        return new FcnRcdValue(dom,vals,false);
      }
 else       if (vchanged) {
        return new FcnRcdValue(this.domain,vals,true);
      }
    }
 else {
      if (vchanged) {
        return new FcnRcdValue(this.intv,vals);
      }
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
