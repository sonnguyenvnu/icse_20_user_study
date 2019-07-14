/** 
 * Gets the list of constraints of a particular type for a property
 * @param pid:the property to retrieve the constraints for
 * @param qid:the type of the constraints
 * @return the stream of matching constraint statements
 */
protected Stream<Statement> getConstraintsByType(PropertyIdValue pid,String qid){
  Stream<Statement> allConstraints=getConstraintStatements(pid).stream().filter(s -> s.getValue() != null && ((EntityIdValue)s.getValue()).getId().equals(qid)).filter(s -> !StatementRank.DEPRECATED.equals(s.getRank()));
  return allConstraints;
}
