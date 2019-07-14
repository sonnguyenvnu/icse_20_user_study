@Override public EdgeLabel addProperties(EdgeLabel edgeLabel,PropertyKey... keys){
  for (  PropertyKey key : keys) {
    if (key.cardinality() != Cardinality.SINGLE) {
      throw new IllegalArgumentException(String.format("An Edge [%s] can not have a property [%s] with the cardinality [%s].",edgeLabel,key,key.cardinality()));
    }
    addSchemaEdge(edgeLabel,key,TypeDefinitionCategory.PROPERTY_KEY_EDGE,null);
  }
  return edgeLabel;
}
