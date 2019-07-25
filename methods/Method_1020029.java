@Override default BiMonoidFactory<B,A,C> flip(){
  return (b,a) -> checkedApply(a,b);
}
