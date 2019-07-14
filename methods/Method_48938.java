public static Map.Entry<RelationType,Collection> extractOrCondition(Or<JanusGraphRelation> condition){
  RelationType masterType=null;
  final List<Object> values=new ArrayList<>();
  for (  final Condition c : condition.getChildren()) {
    if (!(c instanceof PredicateCondition))     return null;
    final PredicateCondition<RelationType,JanusGraphRelation> atom=(PredicateCondition)c;
    if (atom.getPredicate() != Cmp.EQUAL)     return null;
    final Object value=atom.getValue();
    if (value == null)     return null;
    final RelationType type=atom.getKey();
    if (masterType == null)     masterType=type;
 else     if (!masterType.equals(type))     return null;
    values.add(value);
  }
  if (masterType == null)   return null;
  assert !values.isEmpty();
  return new AbstractMap.SimpleImmutableEntry(masterType,values);
}
