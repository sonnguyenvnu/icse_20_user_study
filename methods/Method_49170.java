@Override public boolean containsPropertyKey(String name){
  RelationType type=getRelationType(name);
  return type != null && type.isPropertyKey();
}
