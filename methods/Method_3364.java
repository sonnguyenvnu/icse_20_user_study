private static Object consumeArgumentValue(String name,Class<?> type,Argument argument,Iterator<String> i){
  Object value;
  if (type == Boolean.TYPE || type == Boolean.class) {
    value=true;
  }
 else {
    if (i.hasNext()) {
      value=i.next();
      i.remove();
    }
 else {
      throw new IllegalArgumentException("?flag???????: " + argument.prefix() + name);
    }
  }
  return value;
}
