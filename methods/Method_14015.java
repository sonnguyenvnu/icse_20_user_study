/** 
 * Group added statements in StatementGroups: useful if the item is new.
 * @return a grouped version of getAddedStatements()
 */
public List<StatementGroup> getAddedStatementGroups(){
  Map<PropertyIdValue,List<Statement>> map=new HashMap<>();
  for (  Statement statement : getAddedStatements()) {
    PropertyIdValue propertyId=statement.getClaim().getMainSnak().getPropertyId();
    if (!map.containsKey(propertyId)) {
      map.put(propertyId,new ArrayList<Statement>());
    }
    map.get(propertyId).add(statement);
  }
  List<StatementGroup> result=new ArrayList<>();
  for (  Map.Entry<PropertyIdValue,List<Statement>> entry : map.entrySet()) {
    result.add(new StatementGroupImpl(entry.getValue()));
  }
  return result;
}
