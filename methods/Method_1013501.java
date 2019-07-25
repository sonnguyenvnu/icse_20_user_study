public final Value apply(Value arg,int control){
  try {
    if (!(arg instanceof StringValue)) {
      Assert.fail("Attempted to apply record to a non-string value " + Values.ppr(arg.toString()) + ".");
    }
    UniqueString name=((StringValue)arg).getVal();
    int rlen=this.names.length;
    for (int i=0; i < rlen; i++) {
      if (name.equals(this.names[i])) {
        return this.values[i];
      }
    }
    Assert.fail("Attempted to apply the record\n" + Values.ppr(this.toString()) + "\nto nonexistent record field " + name + ".");
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
