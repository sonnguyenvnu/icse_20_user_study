public Object calculate(){
  Object obj=fetchVar();
  if (obj == null) {
    throw new ElException("obj is NULL, can't call obj." + right);
  }
  if (obj instanceof Map) {
    Map<?,?> om=(Map<?,?>)obj;
    if (om.containsKey(right.toString())) {
      return om.get(right.toString());
    }
  }
  if (obj instanceof Context) {
    Context sc=(Context)obj;
    if (sc.has(right.toString())) {
      return sc.get(right.toString());
    }
  }
  Mirror<?> me=Mirror.me(obj);
  return me.getValue(obj,right.toString());
}
