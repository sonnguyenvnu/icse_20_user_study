public static IntValue Divide(IntValue x,IntValue y){
  int n1=x.val;
  int n2=y.val;
  if (n2 == 0) {
    throw new EvalException(EC.TLC_MODULE_DIVISION_BY_ZERO);
  }
  int q=n1 / n2;
  if ((q < 0) && (q * n2 != n1))   q--;
  return IntValue.gen(q);
}
