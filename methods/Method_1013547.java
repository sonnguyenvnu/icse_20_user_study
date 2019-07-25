public final IValue permute(IMVPerm perm){
  try {
    this.inVal=this.toSetEnum();
    this.tool=null;
    return this.inVal.permute(perm);
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
