/** 
 * Returns a string representation of the schema generation code. This request string is submitted to the Gremlin Server via a client connection to create the schema on the graph instance running on the server.
 */
protected String createSchemaRequest(){
  final StringBuilder s=new StringBuilder();
  s.append("JanusGraphManagement management = graph.openManagement(); ");
  s.append("boolean created = false; ");
  s.append("if (management.getRelationTypes(RelationType.class).iterator().hasNext()) { management.rollback(); created = false; } else { ");
  s.append("PropertyKey name = management.makePropertyKey(\"name\").dataType(String.class).make(); ");
  s.append("PropertyKey age = management.makePropertyKey(\"age\").dataType(Integer.class).make(); ");
  s.append("PropertyKey time = management.makePropertyKey(\"time\").dataType(Integer.class).make(); ");
  s.append("PropertyKey reason = management.makePropertyKey(\"reason\").dataType(String.class).make(); ");
  s.append("PropertyKey place = management.makePropertyKey(\"place\").dataType(Geoshape.class).make(); ");
  s.append("management.makeVertexLabel(\"titan\").make(); ");
  s.append("management.makeVertexLabel(\"location\").make(); ");
  s.append("management.makeVertexLabel(\"god\").make(); ");
  s.append("management.makeVertexLabel(\"demigod\").make(); ");
  s.append("management.makeVertexLabel(\"human\").make(); ");
  s.append("management.makeVertexLabel(\"monster\").make(); ");
  s.append("management.makeEdgeLabel(\"father\").multiplicity(Multiplicity.MANY2ONE).make(); ");
  s.append("management.makeEdgeLabel(\"mother\").multiplicity(Multiplicity.MANY2ONE).make(); ");
  s.append("management.makeEdgeLabel(\"lives\").signature(reason).make(); ");
  s.append("management.makeEdgeLabel(\"pet\").make(); ");
  s.append("management.makeEdgeLabel(\"brother\").make(); ");
  s.append("management.makeEdgeLabel(\"battled\").make(); ");
  s.append("management.buildIndex(\"nameIndex\", Vertex.class).addKey(name).buildCompositeIndex(); ");
  if (useMixedIndex) {
    s.append("management.buildIndex(\"vAge\", Vertex.class).addKey(age).buildMixedIndex(\"" + mixedIndexConfigName + "\"); ");
    s.append("management.buildIndex(\"eReasonPlace\", Edge.class).addKey(reason).addKey(place).buildMixedIndex(\"" + mixedIndexConfigName + "\"); ");
  }
  s.append("management.commit(); created = true; }");
  return s.toString();
}
