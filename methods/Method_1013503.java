public final boolean assign(UniqueString name,Value val){
  try {
    for (int i=0; i < this.names.length; i++) {
      if (name.equals(this.names[i])) {
        if (this.values[i] == UndefValue.ValUndef || this.values[i].equals(val)) {
          this.values[i]=val;
          return true;
        }
        return false;
      }
    }
    Assert.fail("Attempted to assign to nonexistent record field " + name + ".");
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
