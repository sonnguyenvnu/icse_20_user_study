private static boolean coversAll(final MixedIndexType index,Condition<JanusGraphElement> condition,IndexSerializer indexInfo){
  if (condition.getType() != Condition.Type.LITERAL) {
    return StreamSupport.stream(condition.getChildren().spliterator(),false).allMatch(child -> coversAll(index,child,indexInfo));
  }
  if (!(condition instanceof PredicateCondition)) {
    return false;
  }
  final PredicateCondition<RelationType,JanusGraphElement> atom=(PredicateCondition)condition;
  if (atom.getValue() == null) {
    return false;
  }
  Preconditions.checkArgument(atom.getKey().isPropertyKey());
  final PropertyKey key=(PropertyKey)atom.getKey();
  final ParameterIndexField[] fields=index.getFieldKeys();
  final ParameterIndexField match=Arrays.stream(fields).filter(field -> field.getStatus() == SchemaStatus.ENABLED).filter(field -> field.getFieldKey().equals(key)).findAny().orElse(null);
  return match != null && indexInfo.supports(index,match,atom.getPredicate());
}
