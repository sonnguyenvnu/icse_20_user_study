public final IValue permute(IMVPerm perm){
  try {
    IValue res=perm.get(this);
    if (res == null)     return this;
    return res;
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
