/** 
 * Constructs a condition that is equivalent to the type constraints of this query if there are any.
 * @param types
 * @return
 */
private static Condition<JanusGraphRelation> getTypeCondition(Set<RelationType> types){
  assert !types.isEmpty();
  if (types.size() == 1)   return new RelationTypeCondition<>(types.iterator().next());
  final Or<JanusGraphRelation> typeCond=new Or<>(types.size());
  for (  RelationType type : types)   typeCond.add(new RelationTypeCondition<>(type));
  return typeCond;
}
