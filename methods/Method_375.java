private boolean correctTypeName(Type type,String paramTypeName){
  String s=type.getClassName();
  String braces="";
  while (s.endsWith("[]")) {
    braces=braces + "[";
    s=s.substring(0,s.length() - 2);
  }
  if (!braces.equals("")) {
    if (primitives.containsKey(s)) {
      s=braces + primitives.get(s);
    }
 else {
      s=braces + "L" + s + ";";
    }
  }
  return s.equals(paramTypeName);
}
