@Override public Element _apply(final Row row){
  final Element element;
  final String group=row.getAs(SchemaToStructTypeConverter.GROUP);
  final Object source=ArrayUtils.contains(row.schema().fieldNames(),SchemaToStructTypeConverter.SRC_COL_NAME) ? row.getAs(SchemaToStructTypeConverter.SRC_COL_NAME) : null;
  if (null != source) {
    final Object destination=row.getAs(SchemaToStructTypeConverter.DST_COL_NAME);
    final boolean directed=row.getAs(SchemaToStructTypeConverter.DIRECTED_COL_NAME);
    final MatchedVertex matchedVertex;
    if (ArrayUtils.contains(row.schema().fieldNames(),SchemaToStructTypeConverter.MATCHED_VERTEX_COL_NAME)) {
      final String matchedVertexStr=row.getAs(SchemaToStructTypeConverter.MATCHED_VERTEX_COL_NAME);
      matchedVertex=null != matchedVertexStr ? MatchedVertex.valueOf(matchedVertexStr) : null;
    }
 else {
      matchedVertex=null;
    }
    element=new Edge(group,source,destination,directed,matchedVertex,null);
  }
 else {
    final Object vertex=ArrayUtils.contains(row.schema().fieldNames(),SchemaToStructTypeConverter.VERTEX_COL_NAME) ? row.getAs(SchemaToStructTypeConverter.VERTEX_COL_NAME) : row.getAs(SchemaToStructTypeConverter.ID);
    element=new Entity(group,vertex);
  }
  getPropertyNames(row).forEach(n -> {
    element.putProperty(n,row.getAs(n));
  }
);
  return element;
}
