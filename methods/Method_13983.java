/** 
 * Returns the values of a given property in qualifiers
 * @param groups:the qualifiers
 * @param pid:the property to filter on
 * @return
 */
protected List<Value> findValues(List<SnakGroup> groups,String pid){
  List<Value> results=new ArrayList<>();
  for (  SnakGroup group : groups) {
    if (group.getProperty().getId().equals(pid)) {
      for (      Snak snak : group.getSnaks())       results.add(snak.getValue());
    }
  }
  return results;
}
