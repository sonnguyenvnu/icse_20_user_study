private static <E extends JanusGraphElement>void addConstraint(RelationType type,JanusGraphPredicate predicate,Object value,MultiCondition<E> conditions,StandardJanusGraphTx tx){
  if (type.isPropertyKey()) {
    if (value != null)     value=tx.verifyAttribute((PropertyKey)type,value);
  }
 else {
    Preconditions.checkArgument(value instanceof JanusGraphVertex);
  }
  final PredicateCondition<RelationType,E> pc=new PredicateCondition<>(type,predicate,value);
  if (!conditions.contains(pc))   conditions.add(pc);
}
