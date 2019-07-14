/** 
 * Makes question articles showing filter.
 * @return filter the article showing to user
 */
private CompositeFilter makeQuestionArticleShowingFilter(){
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Article.ARTICLE_TYPE,FilterOperator.EQUAL,Article.ARTICLE_TYPE_C_QNA));
  filters.add(new PropertyFilter(Article.ARTICLE_STATUS,FilterOperator.NOT_EQUAL,Article.ARTICLE_STATUS_C_INVALID));
  filters.add(new PropertyFilter(Article.ARTICLE_TAGS,FilterOperator.NOT_EQUAL,Tag.TAG_TITLE_C_SANDBOX));
  filters.add(new PropertyFilter(Article.ARTICLE_SHOW_IN_LIST,FilterOperator.NOT_EQUAL,Article.ARTICLE_SHOW_IN_LIST_C_NOT));
  return new CompositeFilter(CompositeFilterOperator.AND,filters);
}
