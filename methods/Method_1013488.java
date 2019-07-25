public final Value select(Value arg){
  try {
    throw new WrongInvocationException("Error(TLC): attempted to call OpLambdaValue.select().");
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
