@Override public JanusGraphManagement.IndexBuilder buildIndex(String indexName,Class<? extends Element> elementType){
  return new IndexBuilder(indexName,ElementCategory.getByClazz(elementType));
}
