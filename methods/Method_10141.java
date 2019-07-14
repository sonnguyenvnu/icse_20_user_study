/** 
 * Gets a verifycode with the specified code.
 * @param code the specified code
 * @return verifycode, returns {@code null} if not found
 */
public JSONObject getVerifycode(final String code){
  final Query query=new Query().setFilter(new PropertyFilter(Verifycode.CODE,FilterOperator.EQUAL,code));
  try {
    final JSONObject result=verifycodeRepository.get(query);
    final JSONArray codes=result.optJSONArray(Keys.RESULTS);
    if (0 == codes.length()) {
      return null;
    }
    return codes.optJSONObject(0);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets verifycode error",e);
    return null;
  }
}
