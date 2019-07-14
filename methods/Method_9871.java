@Override public List<JSONObject> getRandomly(final int fetchSize) throws RepositoryException {
  final List<JSONObject> ret=new ArrayList<>();
  final double mid=Math.random();
  Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Article.ARTICLE_RANDOM_DOUBLE,FilterOperator.GREATER_THAN_OR_EQUAL,mid),new PropertyFilter(Article.ARTICLE_RANDOM_DOUBLE,FilterOperator.LESS_THAN_OR_EQUAL,mid),new PropertyFilter(Article.ARTICLE_STATUS,FilterOperator.NOT_EQUAL,Article.ARTICLE_STATUS_C_INVALID),new PropertyFilter(Article.ARTICLE_TYPE,FilterOperator.NOT_EQUAL,Article.ARTICLE_TYPE_C_DISCUSSION),new PropertyFilter(Article.ARTICLE_SHOW_IN_LIST,FilterOperator.NOT_EQUAL,Article.ARTICLE_SHOW_IN_LIST_C_NOT))).select(Article.ARTICLE_TITLE,Article.ARTICLE_PERMALINK,Article.ARTICLE_AUTHOR_ID).setPage(1,fetchSize).setPageCount(1);
  final List<JSONObject> list1=getList(query);
  ret.addAll(list1);
  final int reminingSize=fetchSize - list1.size();
  if (0 != reminingSize) {
    query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Article.ARTICLE_RANDOM_DOUBLE,FilterOperator.GREATER_THAN_OR_EQUAL,0D),new PropertyFilter(Article.ARTICLE_RANDOM_DOUBLE,FilterOperator.LESS_THAN_OR_EQUAL,mid),new PropertyFilter(Article.ARTICLE_STATUS,FilterOperator.NOT_EQUAL,Article.ARTICLE_STATUS_C_INVALID),new PropertyFilter(Article.ARTICLE_TYPE,FilterOperator.NOT_EQUAL,Article.ARTICLE_TYPE_C_DISCUSSION),new PropertyFilter(Article.ARTICLE_SHOW_IN_LIST,FilterOperator.NOT_EQUAL,Article.ARTICLE_SHOW_IN_LIST_C_NOT))).select(Article.ARTICLE_TITLE,Article.ARTICLE_PERMALINK,Article.ARTICLE_AUTHOR_ID).setPage(1,reminingSize).setPageCount(1);
    final List<JSONObject> list2=getList(query);
    ret.addAll(list2);
  }
  return ret;
}
