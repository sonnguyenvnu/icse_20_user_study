private int computeLayoutParamsHashCode(){
  int result=1;
  for (int i=0, N=getChildCount(); i < N; i++) {
    Child c=getChildAt(i);
    LayoutParams lp=c.getLayoutParams();
    result=31 * result + lp.hashCode();
  }
  return result;
}
