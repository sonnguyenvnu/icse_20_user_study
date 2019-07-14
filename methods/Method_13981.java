/** 
 * Returns a single constraint for a particular type and a property, or null if there is no such constraint
 * @param pid:the property to retrieve the constraints for
 * @param qid:the type of the constraints
 * @return the list of qualifiers for the constraint, or null if it does notexist
 */
protected List<SnakGroup> getSingleConstraint(PropertyIdValue pid,String qid){
  Statement statement=getConstraintsByType(pid,qid).findFirst().orElse(null);
  if (statement != null) {
    return statement.getClaim().getQualifiers();
  }
  return null;
}
