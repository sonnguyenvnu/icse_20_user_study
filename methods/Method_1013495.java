public final Value select(Value arg){
  try {
    Assert.fail("Attempted to call OpRcdValue.select(). This is a TLC bug.");
    return null;
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
