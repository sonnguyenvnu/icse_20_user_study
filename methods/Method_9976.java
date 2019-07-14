/** 
 * Gets all written character count.
 * @return all written character count
 */
public int getWrittenCharacterCount(){
  try {
    final List<JSONObject> result=characterRepository.select("select count(DISTINCT characterContent) from " + characterRepository.getName());
    if (null == result || result.isEmpty()) {
      return 0;
    }
    return result.get(0).optInt("count(DISTINCT characterContent)");
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Counts characters failed",e);
    return 0;
  }
}
