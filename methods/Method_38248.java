protected Object resolveValueInSpecialCase(Object value,final String name){
  String[] elements=StringUtil.splitc(name,'.');
  for (  String element : elements) {
    value=BeanUtil.declaredSilent.getProperty(value,element);
    if (value instanceof List) {
      List list=(List)value;
      value=list.get(list.size() - 1);
    }
  }
  return value;
}
