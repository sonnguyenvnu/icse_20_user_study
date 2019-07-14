private void updateArticleSearchIndex(final JSONObject article){
  if (null == article || Article.ARTICLE_TYPE_C_DISCUSSION == article.optInt(Article.ARTICLE_TYPE) || Article.ARTICLE_TYPE_C_THOUGHT == article.optInt(Article.ARTICLE_TYPE)) {
    return;
  }
  if (Symphonys.ALGOLIA_ENABLED) {
    searchMgmtService.updateAlgoliaDocument(article);
  }
  if (Symphonys.ES_ENABLED) {
    searchMgmtService.updateESDocument(article,Article.ARTICLE);
  }
  final String articlePermalink=Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK);
  ArticleBaiduSender.sendToBaidu(articlePermalink);
}
