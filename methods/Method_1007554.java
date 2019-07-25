private boolean proxy(String cname){
  return proxy != null && proxy.findResource(cname) == null;
}
