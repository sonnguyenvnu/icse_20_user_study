private boolean isValidMethodName(String name){
  return !"getClass".equals(name) && !"getDeclaringClass".equals(name);
}
