public final IValue permute(IMVPerm perm){
  try {
    this.convertAndCache();
    return this.diffSet.permute(perm);
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
