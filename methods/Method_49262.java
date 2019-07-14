@Override public void makePropertyConstraintForVertex(VertexLabel vertexLabel,PropertyKey key,SchemaManager manager){
  throw new IllegalArgumentException(String.format("Property Key constraint does not exist for given Vertex Label [%s] and property key [%s].",vertexLabel,key));
}
