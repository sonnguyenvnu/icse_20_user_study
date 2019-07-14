public void updateSchemaVertex(JanusGraphSchemaVertex schemaVertex){
  addProperty(VertexProperty.Cardinality.single,schemaVertex,BaseKey.SchemaUpdateTime,times.getTime(times.getTime()));
}
