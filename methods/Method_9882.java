/** 
 * Gets a liveness by the specified user id and date.
 * @param userId the specified user id
 * @param date   the specified date
 * @return a liveness, {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByUserAndDate(final String userId,final String date) throws RepositoryException {
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Liveness.LIVENESS_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Liveness.LIVENESS_DATE,FilterOperator.EQUAL,date))).setPageCount(1);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  return array.optJSONObject(0);
}
