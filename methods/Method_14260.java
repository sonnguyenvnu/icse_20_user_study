static public boolean isArrayOrList(Object v){
  return v != null && (v.getClass().isArray() || v instanceof List<?>);
}
