public final int size(){
  long sz=1;
  for (int i=0; i < this.domains.length; i++) {
    int sz1=this.domains[i].size();
    if (!this.isTuples[i]) {
      int len1=this.formals[i].length;
      for (int j=1; j < len1; j++) {
        sz*=sz1;
        if (sz < -2147483648 || sz > 2147483647) {
          Assert.fail(EC.TLC_MODULE_OVERFLOW,"the number of elements in:\n" + this.toString());
        }
      }
    }
    sz*=sz1;
    if (sz < -2147483648 || sz > 2147483647) {
      Assert.fail(EC.TLC_MODULE_OVERFLOW,"the number of elements in:\n" + this.toString());
    }
  }
  return (int)sz;
}
