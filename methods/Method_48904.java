public static List<Object[]> indexCover(final CompositeIndexType index,Condition<JanusGraphElement> condition,Set<Condition> covered){
  if (!QueryUtil.isQueryNormalForm(condition)) {
    return null;
  }
  assert condition instanceof And;
  if (index.getStatus() != SchemaStatus.ENABLED)   return null;
  final IndexField[] fields=index.getFieldKeys();
  final Object[] indexValues=new Object[fields.length];
  final Set<Condition> coveredClauses=new HashSet<>(fields.length);
  final List<Object[]> indexCovers=new ArrayList<>(4);
  constructIndexCover(indexValues,0,fields,condition,indexCovers,coveredClauses);
  if (!indexCovers.isEmpty()) {
    covered.addAll(coveredClauses);
    return indexCovers;
  }
 else   return null;
}
