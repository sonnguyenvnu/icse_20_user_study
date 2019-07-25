public final boolean assignable(Value val){
  try {
    if (this.val == null || this.val == UndefValue.ValUndef) {
      Assert.fail("Error(TLC): Attempted to call assignable on lazy value.");
    }
    return this.val.assignable(val);
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
