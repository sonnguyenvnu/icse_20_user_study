public final Value select(Value arg){
  try {
    throw new WrongInvocationException("It is a TLC bug: Attempted to call MethodValue.select().");
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
