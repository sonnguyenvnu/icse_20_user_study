/** 
 * Gets following tag articles.
 * @param userId         the specified user id
 * @param currentPageNum the specified page number
 * @param pageSize       the specified page size
 * @return following tag articles, returns an empty list if not found
 */
public List<JSONObject> getFollowingTagArticles(final String userId,final int currentPageNum,final int pageSize){
  final List<JSONObject> tags=(List<JSONObject>)followQueryService.getFollowingTags(userId,1,Integer.MAX_VALUE).opt(Keys.RESULTS);
  if (tags.isEmpty()) {
    return Collections.emptyList();
  }
  final List<String> articleFields=new ArrayList<>();
  articleFields.add(Keys.OBJECT_ID);
  articleFields.add(Article.ARTICLE_STICK);
  articleFields.add(Article.ARTICLE_CREATE_TIME);
  articleFields.add(Article.ARTICLE_UPDATE_TIME);
  articleFields.add(Article.ARTICLE_LATEST_CMT_TIME);
  articleFields.add(Article.ARTICLE_AUTHOR_ID);
  articleFields.add(Article.ARTICLE_TITLE);
  articleFields.add(Article.ARTICLE_STATUS);
  articleFields.add(Article.ARTICLE_VIEW_CNT);
  articleFields.add(Article.ARTICLE_TYPE);
  articleFields.add(Article.ARTICLE_PERMALINK);
  articleFields.add(Article.ARTICLE_TAGS);
  articleFields.add(Article.ARTICLE_LATEST_CMTER_NAME);
  articleFields.add(Article.ARTICLE_COMMENT_CNT);
  articleFields.add(Article.ARTICLE_ANONYMOUS);
  articleFields.add(Article.ARTICLE_PERFECT);
  articleFields.add(Article.ARTICLE_CONTENT);
  articleFields.add(Article.ARTICLE_QNA_OFFER_POINT);
  articleFields.add(Article.ARTICLE_SHOW_IN_LIST);
  return getArticlesByTags(currentPageNum,pageSize,articleFields,tags.toArray(new JSONObject[0]));
}
