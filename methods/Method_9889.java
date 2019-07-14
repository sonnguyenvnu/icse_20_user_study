/** 
 * Gets tag-article relations by the specified article id.
 * @param articleId the specified article id
 * @return for example      <pre>[{ "oId": "", "tag_oId": "", "article_oId": articleId }, ....], returns an empty list if not found </pre>
 * @throws RepositoryException repository exception
 */
public List<JSONObject> getByArticleId(final String articleId) throws RepositoryException {
  final Query query=new Query().setFilter(new PropertyFilter(Article.ARTICLE + "_" + Keys.OBJECT_ID,FilterOperator.EQUAL,articleId)).setPageCount(1);
  return getList(query);
}
