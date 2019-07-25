@SuppressWarnings("rawtypes") public Object calculate(){
  Object lval=calculateItem(left);
  Object rval=calculateItem(right);
  if (lval instanceof Map) {
    Map<?,?> om=(Map<?,?>)lval;
    if (om.containsKey(right.toString())) {
      return om.get(right.toString());
    }
  }
 else   if (lval instanceof List) {
    return ((List)lval).get((Integer)rval);
  }
  return Array.get(lval,(Integer)rval);
}
