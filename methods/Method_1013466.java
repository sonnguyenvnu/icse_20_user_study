public final int size(){
  try {
    if (this.val == null || this.val == UndefValue.ValUndef) {
      Assert.fail("Error(TLC): Attempted to compute size of lazy value.");
    }
    return this.val.size();
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
