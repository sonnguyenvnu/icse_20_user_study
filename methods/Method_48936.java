private static <E extends JanusGraphElement>Or<E> addConstraint(final RelationType type,OrJanusPredicate predicate,List<Object> values,Or<E> or,StandardJanusGraphTx tx){
  for (int i=0; i < values.size(); i++) {
    final JanusGraphPredicate janusGraphPredicate=predicate.get(i);
    if (janusGraphPredicate instanceof AndJanusPredicate) {
      final List<Object> childValues=(List<Object>)(values.get(i));
      final And<E> nested=addConstraint(type,(AndJanusPredicate)janusGraphPredicate,childValues,new And<>(childValues.size()),tx);
      if (nested == null) {
        return null;
      }
      or.add(nested);
    }
 else     if (janusGraphPredicate instanceof OrJanusPredicate) {
      if (addConstraint(type,(OrJanusPredicate)janusGraphPredicate,(List<Object>)(values.get(i)),or,tx) == null) {
        return null;
      }
    }
 else {
      addConstraint(type,janusGraphPredicate,values.get(i),or,tx);
    }
  }
  return or;
}
