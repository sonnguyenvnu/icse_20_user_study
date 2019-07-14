/** 
 * Extracts all the new entities mentioned by this snak group.
 * @param snakGroup
 * @return
 */
public Set<ReconItemIdValue> extractPointers(SnakGroup snakGroup){
  Set<ReconItemIdValue> result=new HashSet<>();
  snakGroup.getSnaks().stream().map(s -> extractPointers(s)).forEach(s -> result.addAll(s));
  return result;
}
