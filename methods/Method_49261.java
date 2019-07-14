@Override public void makePropertyConstraintForEdge(EdgeLabel edgeLabel,PropertyKey key,SchemaManager manager){
  throw new IllegalArgumentException(String.format("Property Key constraint does not exist for given Edge Label [%s] and property key [%s].",edgeLabel,key));
}
