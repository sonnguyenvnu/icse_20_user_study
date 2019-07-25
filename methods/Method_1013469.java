public final IValue permute(IMVPerm perm){
  try {
    if (this.val == null || this.val == UndefValue.ValUndef) {
      Assert.fail("Error(TLC): Attempted to apply permutation to lazy value.");
    }
    return this.val.permute(perm);
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
