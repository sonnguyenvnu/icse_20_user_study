public final Value normalize(){
  try {
    if (this.val == null || this.val == UndefValue.ValUndef) {
      Assert.fail("Error(TLC): Attempted to normalize lazy value.");
    }
    this.val.normalize();
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
