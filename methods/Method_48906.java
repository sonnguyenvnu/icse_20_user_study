public static Condition<JanusGraphElement> indexCover(final MixedIndexType index,Condition<JanusGraphElement> condition,final IndexSerializer indexInfo,final Set<Condition> covered){
  if (!indexInfo.features(index).supportNotQueryNormalForm() && !QueryUtil.isQueryNormalForm(condition)) {
    return null;
  }
  if (condition instanceof Or) {
    for (    final Condition<JanusGraphElement> subClause : condition.getChildren()) {
      if (subClause instanceof And) {
        for (        final Condition<JanusGraphElement> subsubClause : condition.getChildren()) {
          if (!coversAll(index,subsubClause,indexInfo)) {
            return null;
          }
        }
      }
 else {
        if (!coversAll(index,subClause,indexInfo)) {
          return null;
        }
      }
    }
    covered.add(condition);
    return condition;
  }
  assert condition instanceof And;
  final And<JanusGraphElement> subCondition=new And<>(condition.numChildren());
  for (  final Condition<JanusGraphElement> subClause : condition.getChildren()) {
    if (coversAll(index,subClause,indexInfo)) {
      subCondition.add(subClause);
      covered.add(subClause);
    }
  }
  return subCondition.isEmpty() ? null : subCondition;
}
