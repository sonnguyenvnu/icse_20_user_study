/** 
 * Gets a verifycode with the specified type, biz type and user id.
 * @param type    the specified type
 * @param bizType the specified biz type
 * @param userId  the specified user id
 * @return verifycode, returns {@code null} if not found
 */
public JSONObject getVerifycodeByUserId(final int type,final int bizType,final String userId){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Verifycode.TYPE,FilterOperator.EQUAL,type),new PropertyFilter(Verifycode.BIZ_TYPE,FilterOperator.EQUAL,bizType),new PropertyFilter(Verifycode.USER_ID,FilterOperator.EQUAL,userId))).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING);
  try {
    final JSONObject result=verifycodeRepository.get(query);
    final JSONArray codes=result.optJSONArray(Keys.RESULTS);
    if (0 == codes.length()) {
      return null;
    }
    return codes.optJSONObject(0);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets verifycode failed",e);
    return null;
  }
}
