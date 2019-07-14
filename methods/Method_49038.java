@Override public EdgeLabelDefinition getEdgeLabel(String name){
  RelationTypeDefinition def=getRelationType(name);
  if (def != null && !(def instanceof EdgeLabelDefinition))   throw new IllegalArgumentException("Not an edge label but property key: " + name);
  return (EdgeLabelDefinition)def;
}
