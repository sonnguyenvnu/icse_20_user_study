/** 
 * Prepares the constraints from the query builder into a QNF compliant condition. If the condition is invalid or trivially false, it returns null.
 * @param tx
 * @param constraints
 * @param < E >
 * @return
 * @see #isQueryNormalForm(org.janusgraph.graphdb.query.condition.Condition)
 */
public static <E extends JanusGraphElement>And<E> constraints2QNF(StandardJanusGraphTx tx,List<PredicateCondition<String,E>> constraints){
  final And<E> conditions=new And<>(constraints.size() + 4);
  for (  final PredicateCondition<String,E> atom : constraints) {
    final RelationType type=getType(tx,atom.getKey());
    if (type == null) {
      if (atom.getPredicate() == Cmp.EQUAL && atom.getValue() == null || (atom.getPredicate() == Cmp.NOT_EQUAL && atom.getValue() != null))       continue;
      return null;
    }
    final Object value=atom.getValue();
    final JanusGraphPredicate predicate=atom.getPredicate();
    if (type.isPropertyKey()) {
      final PropertyKey key=(PropertyKey)type;
      assert predicate.isValidCondition(value);
      Preconditions.checkArgument(key.dataType() == Object.class || predicate.isValidValueType(key.dataType()),"Data type of key is not compatible with condition");
    }
 else {
      Preconditions.checkArgument(((EdgeLabel)type).isUnidirected());
      Preconditions.checkArgument(predicate.isValidValueType(JanusGraphVertex.class),"Data type of key is not compatible with condition");
    }
    if (predicate instanceof Contain) {
      final Collection values=(Collection)value;
      if (predicate == Contain.NOT_IN) {
        if (values.isEmpty())         continue;
        for (        final Object inValue : values)         addConstraint(type,Cmp.NOT_EQUAL,inValue,conditions,tx);
      }
 else {
        Preconditions.checkArgument(predicate == Contain.IN);
        if (values.isEmpty()) {
          return null;
        }
        if (values.size() == 1) {
          addConstraint(type,Cmp.EQUAL,values.iterator().next(),conditions,tx);
        }
 else {
          final Or<E> nested=new Or<>(values.size());
          for (          final Object invalue : values)           addConstraint(type,Cmp.EQUAL,invalue,nested,tx);
          conditions.add(nested);
        }
      }
    }
 else     if (predicate instanceof AndJanusPredicate) {
      if (addConstraint(type,(AndJanusPredicate)(predicate),(List<Object>)(value),conditions,tx) == null) {
        return null;
      }
    }
 else     if (predicate instanceof OrJanusPredicate) {
      final List<Object> values=(List<Object>)(value);
      final Or<E> nested=addConstraint(type,(OrJanusPredicate)predicate,values,new Or<>(values.size()),tx);
      if (nested == null) {
        return null;
      }
      conditions.add(nested);
    }
 else {
      addConstraint(type,predicate,value,conditions,tx);
    }
  }
  return conditions;
}
