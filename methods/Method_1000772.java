public ValueProxy make(IocMaking ing,IocValue iv){
  if (iv.getValue() == null)   return null;
  String value=iv.getValue().toString();
  if ("app".equals(iv.getType())) {
    if ("$servlet".equalsIgnoreCase(value))     return new StaticValue(sc);
    return new StaticValue(sc.getAttribute(value));
  }
  return null;
}
