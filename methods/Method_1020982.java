@Override public void intercept(Invocation inv){
  JbootAopInvocation invocation=new JbootAopInvocation(inv);
  invocation.invoke();
}
