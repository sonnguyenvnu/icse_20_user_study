public final boolean assignable(Value val){
  try {
    throw new WrongInvocationException("Should not initialize an operator.");
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
