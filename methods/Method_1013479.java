public final boolean assignable(Value val){
  try {
    return ((val instanceof ModelValue) && this.val.equals(((ModelValue)val).val));
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
