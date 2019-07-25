public final Value normalize(){
  try {
    throw new WrongInvocationException("It is a TLC bug: Attempted to normalize an operator.");
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
