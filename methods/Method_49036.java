public Iterable<EdgeLabelDefinition> getEdgeLabels(){
  return Iterables.filter(relationTypes.values(),EdgeLabelDefinition.class);
}
