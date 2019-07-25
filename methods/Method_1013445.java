public final IValue permute(IMVPerm perm){
  try {
    Value fcn=this.toFcnRcd();
    return fcn.permute(perm);
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
