private Object[] objects(TypedValue[] parameters){
  return Arrays.stream(parameters).map(TypedValue::getObj).toArray();
}
