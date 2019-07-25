protected void _check(Method method){
  if (fm != null)   return;
synchronized (this) {
    if (fm != null)     return;
    fm=FastClassFactory.get(method);
  }
}
