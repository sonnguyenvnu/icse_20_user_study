public Integer call(){
  obj=0.0;
  err=zeroone=0;
  if (expected == null) {
    expected=new double[wSize];
  }
  Arrays.fill(expected,0.0);
  for (int i=start_i; i < size; i=i + threadNum) {
    obj+=x.get(i).gradient(expected);
    int errorNum=x.get(i).eval();
    x.get(i).clearNodes();
    err+=errorNum;
    if (errorNum != 0) {
      ++zeroone;
    }
  }
  return err;
}
