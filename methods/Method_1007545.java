private String type(Object o){
  if (o == null) {
    return "null";
  }
 else   if (o instanceof Integer) {
switch (((Integer)o).intValue()) {
case 0:
      return "Top";
case 1:
    return "Integer";
case 2:
  return "Float";
case 3:
return "Double";
case 4:
return "Long";
case 5:
return "Null";
case 6:
return "Uninitialized_This";
}
}
 else if (o instanceof String) {
return (String)o;
}
return "??UNKNOWN??" + o.getClass() + ":" + o;
}
