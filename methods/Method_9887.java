/** 
 * Gets a referral by the specified data id and IP.
 * @param dataId the specified data id
 * @param ip     the specified IP
 * @return referral, returns {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByDataIdAndIP(final String dataId,final String ip) throws RepositoryException {
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Referral.REFERRAL_DATA_ID,FilterOperator.EQUAL,dataId),new PropertyFilter(Referral.REFERRAL_IP,FilterOperator.EQUAL,ip))).setPageCount(1).setPage(1,1);
  final JSONArray records=get(query).optJSONArray(Keys.RESULTS);
  if (records.length() < 1) {
    return null;
  }
  return records.optJSONObject(0);
}
