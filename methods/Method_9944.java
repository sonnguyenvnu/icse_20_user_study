/** 
 * Makes article showing filters.
 * @return filter the article showing to user
 */
private CompositeFilter makeArticleShowingFilter(){
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Article.ARTICLE_STATUS,FilterOperator.NOT_EQUAL,Article.ARTICLE_STATUS_C_INVALID));
  filters.add(new PropertyFilter(Article.ARTICLE_TYPE,FilterOperator.NOT_EQUAL,Article.ARTICLE_TYPE_C_DISCUSSION));
  return new CompositeFilter(CompositeFilterOperator.AND,filters);
}
