/** 
 * Removes user-tag relations by the specified user id and tag id.
 * @param userId the specified user id
 * @param tagId  the specified tag id
 * @param type   the specified type
 * @throws RepositoryException repository exception
 */
public void removeByUserIdAndTagId(final String userId,final String tagId,final int type) throws RepositoryException {
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(User.USER + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Tag.TAG + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,tagId),new PropertyFilter(Common.TYPE,FilterOperator.EQUAL,type))).setPage(1,Integer.MAX_VALUE).setPageCount(1);
  final JSONArray rels=get(query).optJSONArray(Keys.RESULTS);
  for (int i=0; i < rels.length(); i++) {
    final String id=rels.optJSONObject(i).optString(Keys.OBJECT_ID);
    remove(id);
  }
}
