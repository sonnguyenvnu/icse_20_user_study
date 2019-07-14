@Override public boolean containsEdgeLabel(String name){
  RelationType type=getRelationType(name);
  return type != null && type.isEdgeLabel();
}
