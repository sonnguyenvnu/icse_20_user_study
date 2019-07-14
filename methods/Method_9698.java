/** 
 * Loads perfect articles.
 */
public void loadPerfectArticles(){
  final BeanManager beanManager=BeanManager.getInstance();
  final ArticleRepository articleRepository=beanManager.getReference(ArticleRepository.class);
  final ArticleQueryService articleQueryService=beanManager.getReference(ArticleQueryService.class);
  Stopwatchs.start("Query perfect articles");
  try {
    final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPageCount(1).setPage(1,36);
    query.setFilter(CompositeFilterOperator.and(new PropertyFilter(Article.ARTICLE_PERFECT,FilterOperator.EQUAL,Article.ARTICLE_PERFECT_C_PERFECT),new PropertyFilter(Article.ARTICLE_SHOW_IN_LIST,FilterOperator.NOT_EQUAL,Article.ARTICLE_SHOW_IN_LIST_C_NOT)));
    query.select(Keys.OBJECT_ID,Article.ARTICLE_STICK,Article.ARTICLE_CREATE_TIME,Article.ARTICLE_UPDATE_TIME,Article.ARTICLE_LATEST_CMT_TIME,Article.ARTICLE_AUTHOR_ID,Article.ARTICLE_TITLE,Article.ARTICLE_STATUS,Article.ARTICLE_VIEW_CNT,Article.ARTICLE_TYPE,Article.ARTICLE_PERMALINK,Article.ARTICLE_TAGS,Article.ARTICLE_LATEST_CMTER_NAME,Article.ARTICLE_COMMENT_CNT,Article.ARTICLE_ANONYMOUS,Article.ARTICLE_PERFECT,Article.ARTICLE_QNA_OFFER_POINT,Article.ARTICLE_SHOW_IN_LIST);
    final JSONObject result=articleRepository.get(query);
    final List<JSONObject> articles=CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
    articleQueryService.organizeArticles(articles);
    PERFECT_ARTICLES.clear();
    PERFECT_ARTICLES.addAll(articles);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Loads perfect articles failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
