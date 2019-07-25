public boolean has(String name){
  if (iocName != null)   return iocName.equals(name);
  return ("$aop_" + _name()).equals(name);
}
