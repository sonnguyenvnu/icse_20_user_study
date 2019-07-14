private StringBuilder toString(final JanusGraphPredicate predicate,final Object value){
  final StringBuilder toReturn=new StringBuilder();
  if (!(predicate instanceof ConnectiveJanusPredicate)) {
    toReturn.append(predicate);
    if (value != null) {
      toReturn.append("(").append(value).append(")");
    }
    return toReturn;
  }
  final ConnectiveJanusPredicate connectivePredicate=(ConnectiveJanusPredicate)predicate;
  final List<Object> values=null == value ? new ArrayList<>() : (List<Object>)value;
  if (connectivePredicate.size() == 1) {
    return toString(connectivePredicate.get(0),values.get(0));
  }
  if (predicate instanceof AndJanusPredicate) {
    toReturn.append("and(");
  }
 else   if (predicate instanceof OrJanusPredicate) {
    toReturn.append("or(");
  }
 else {
    throw new IllegalArgumentException("JanusGraph does not support the given predicate: " + predicate);
  }
  final Iterator<Object> itValues=values.iterator();
  toReturn.append(connectivePredicate.stream().map(p -> toString(p,itValues.next())).collect(Collectors.joining(", "))).append(")");
  return toReturn;
}
