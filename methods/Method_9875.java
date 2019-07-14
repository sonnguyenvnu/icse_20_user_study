/** 
 * Gets a domain by the specified domain URI.
 * @param domainURI the specified domain URI
 * @return a domain, {@code null} if not found
 * @throws RepositoryException repository exception
 */
public JSONObject getByURI(final String domainURI) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Domain.DOMAIN_URI,FilterOperator.EQUAL,domainURI)).setPageCount(1);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  return array.optJSONObject(0);
}
