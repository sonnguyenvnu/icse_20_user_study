static public boolean isTrue(Object o){
  return o != null && (o instanceof Boolean ? ((Boolean)o).booleanValue() : Boolean.parseBoolean(o.toString()));
}
