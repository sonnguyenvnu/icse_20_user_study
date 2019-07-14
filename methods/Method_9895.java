/** 
 * Gets tags of an article specified by the article id.
 * @param articleId the specified article id
 * @return a list of tags of the specified article, returns an empty list if not found
 * @throws RepositoryException repository exception
 */
public List<JSONObject> getByArticleId(final String articleId) throws RepositoryException {
  final List<JSONObject> ret=new ArrayList<>();
  final List<JSONObject> tagArticleRelations=tagArticleRepository.getByArticleId(articleId);
  for (  final JSONObject tagArticleRelation : tagArticleRelations) {
    final String tagId=tagArticleRelation.optString(Tag.TAG + "_" + Keys.OBJECT_ID);
    final JSONObject tag=get(tagId);
    ret.add(tag);
  }
  return ret;
}
