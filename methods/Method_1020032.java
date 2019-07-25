@Override default Monoid<B> apply(A a){
  try {
    return checkedApply(a);
  }
 catch (  Throwable t) {
    throw Runtime.throwChecked(t);
  }
}
