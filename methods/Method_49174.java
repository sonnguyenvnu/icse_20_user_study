@Override public boolean containsVertexLabel(String name){
  verifyOpen();
  return BaseVertexLabel.DEFAULT_VERTEXLABEL.name().equals(name) || getSchemaVertex(JanusGraphSchemaCategory.VERTEXLABEL.getSchemaName(name)) != null;
}
