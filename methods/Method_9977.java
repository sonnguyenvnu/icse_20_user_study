/** 
 * Gets all written characters. <p> <b>Note</b>: Just for testing. </p>
 * @return all written characters
 */
public Set<JSONObject> getWrittenCharacters(){
  try {
    return CollectionUtils.jsonArrayToSet(characterRepository.get(new Query()).optJSONArray(Keys.RESULTS));
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets characters failed",e);
    return Collections.emptySet();
  }
}
