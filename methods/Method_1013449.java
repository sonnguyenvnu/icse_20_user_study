public final Value apply(Value arg,int control){
  try {
    Value result=this.select(arg);
    if (result == null) {
      Assert.fail("Attempted to apply function:\n" + Values.ppr(this.toString()) + "\nto argument " + Values.ppr(arg.toString()) + ", which is" + " not in the domain of the function.");
    }
    return result;
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
