public final Value normalize(){
  try {
    if (!this.isNorm) {
      int len=this.names.length;
      for (int i=1; i < len; i++) {
        int cmp=this.names[0].compareTo(this.names[i]);
        if (cmp == 0) {
          Assert.fail("Field name " + this.names[i] + " occurs multiple times in record.");
        }
 else         if (cmp > 0) {
          UniqueString ts=this.names[0];
          this.names[0]=this.names[i];
          this.names[i]=ts;
          Value tv=this.values[0];
          this.values[0]=this.values[i];
          this.values[i]=tv;
        }
      }
      for (int i=2; i < len; i++) {
        int j=i;
        UniqueString st=this.names[i];
        Value val=this.values[i];
        int cmp;
        while ((cmp=st.compareTo(this.names[j - 1])) < 0) {
          this.names[j]=this.names[j - 1];
          this.values[j]=this.values[j - 1];
          j--;
        }
        if (cmp == 0) {
          Assert.fail("Field name " + this.names[i] + " occurs multiple times in record.");
        }
        this.names[j]=st;
        this.values[j]=val;
      }
      this.isNorm=true;
    }
    return this;
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
