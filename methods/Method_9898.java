/** 
 * Gets a tag-tag relation by the specified tag1 id and tag2 id.
 * @param tag1Id the specified tag1 id
 * @param tag2Id the specified tag2 id
 * @return for example      <pre>{ "oId": "", "tag1_oId": tag1Id, "tag2_oId": tag2Id, "weight": int }, returns  {@code null} if not found</pre>
 * @throws RepositoryException repository exception
 */
public JSONObject getByTag1IdAndTag2Id(final String tag1Id,final String tag2Id) throws RepositoryException {
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Tag.TAG + "1_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tag1Id));
  filters.add(new PropertyFilter(Tag.TAG + "2_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tag2Id));
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  final JSONArray result=get(query).optJSONArray(Keys.RESULTS);
  if (result.length() < 1) {
    return null;
  }
  return result.optJSONObject(0);
}
