public void checkPropertyConstraintForEdgeOrCreatePropertyConstraint(StandardEdge edge,PropertyKey key){
  if (config.hasDisabledSchemaConstraints())   return;
  EdgeLabel edgeLabel=edge.edgeLabel();
  if (edgeLabel instanceof BaseLabel)   return;
  Collection<PropertyKey> propertyKeys=edgeLabel.mappedProperties();
  if (propertyKeys.contains(key))   return;
  config.getAutoSchemaMaker().makePropertyConstraintForEdge(edgeLabel,key,this);
}
