/** 
 * Converts the constraint conditions of this query into a constraintMap which is passed as an argument. If all the constraint conditions could be accounted for in the constraintMap, this method returns true, else - if some constraints cannot be captured in an interval - it returns false to indicate that further in-memory filtering will be necessary. </p> This constraint map is used in constructing the SliceQueries and query optimization since this representation is easier to handle.
 * @param conditions
 * @param constraintMap
 * @return
 */
private boolean compileConstraints(And<JanusGraphRelation> conditions,Map<RelationType,Interval> constraintMap){
  boolean isFitted=true;
  for (  Condition<JanusGraphRelation> condition : conditions.getChildren()) {
    RelationType type=null;
    Interval newInterval=null;
    if (condition instanceof Or) {
      Map.Entry<RelationType,Collection> orEqual=QueryUtil.extractOrCondition((Or)condition);
      if (orEqual != null) {
        type=orEqual.getKey();
        newInterval=new PointInterval(orEqual.getValue());
      }
    }
 else     if (condition instanceof PredicateCondition) {
      PredicateCondition<RelationType,JanusGraphRelation> atom=(PredicateCondition)condition;
      type=atom.getKey();
      Interval interval=constraintMap.get(type);
      newInterval=intersectConstraints(interval,type,atom.getPredicate(),atom.getValue());
    }
    if (newInterval != null) {
      constraintMap.put(type,newInterval);
    }
 else     isFitted=false;
  }
  if (adjacentVertex != null) {
    if (adjacentVertex.hasId()) {
      constraintMap.put(ImplicitKey.ADJACENT_ID,new PointInterval(adjacentVertex.longId()));
    }
 else     isFitted=false;
  }
  return isFitted;
}
