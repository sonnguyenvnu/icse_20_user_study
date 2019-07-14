public EdgeLabel makeEdgeLabel(String name,TypeDefinitionMap definition){
  return (EdgeLabel)makeSchemaVertex(JanusGraphSchemaCategory.EDGELABEL,name,definition);
}
