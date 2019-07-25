/** 
 * Tests if the specified privilege implies, either directly or as an aggregate, the named privilege.
 * @param checked the privilege being checked
 * @param implied the name of the privilege to be implied
 * @return true if the check privilege implies the given privilege name
 */
private static boolean implies(Privilege checked,String implied){
  if (checked.getName().equals(implied)) {
    return true;
  }
 else   if (checked.isAggregate()) {
    return impliesAny(checked.getAggregatePrivileges(),implied);
  }
 else {
    return false;
  }
}
