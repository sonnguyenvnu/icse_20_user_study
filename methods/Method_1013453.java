public final Value normalize(){
  try {
    if (!this.isNorm) {
      int dlen=this.domain.length;
      for (int i=1; i < dlen; i++) {
        int cmp=this.domain[0].compareTo(this.domain[i]);
        if (cmp == 0) {
          Assert.fail("The value\n" + this.domain[i] + "\noccurs multiple times in the function domain.");
        }
 else         if (cmp > 0) {
          Value tv=this.domain[0];
          this.domain[0]=this.domain[i];
          this.domain[i]=tv;
          tv=this.values[0];
          this.values[0]=this.values[i];
          this.values[i]=tv;
        }
      }
      for (int i=2; i < dlen; i++) {
        Value d=this.domain[i];
        Value v=this.values[i];
        int j=i;
        int cmp;
        while ((cmp=d.compareTo(this.domain[j - 1])) < 0) {
          this.domain[j]=this.domain[j - 1];
          this.values[j]=this.values[j - 1];
          j--;
        }
        if (cmp == 0) {
          Assert.fail("The value\n" + this.domain[i] + "\noccurs multiple times in the function domain.");
        }
        this.domain[j]=d;
        this.values[j]=v;
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
