public final boolean member(Value elem){
  try {
    Assert.fail("Attempted to check set membership in a tuple value.");
    return false;
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
