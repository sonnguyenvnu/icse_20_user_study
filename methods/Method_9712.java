@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  final JSONObject article=data.optJSONObject(Article.ARTICLE);
  if (Article.ARTICLE_TYPE_C_DISCUSSION == article.optInt(Article.ARTICLE_TYPE) || Article.ARTICLE_TYPE_C_THOUGHT == article.optInt(Article.ARTICLE_TYPE)) {
    return;
  }
  final String tags=article.optString(Article.ARTICLE_TAGS);
  if (StringUtils.containsIgnoreCase(tags,Tag.TAG_TITLE_C_SANDBOX)) {
    return;
  }
  if (Symphonys.ALGOLIA_ENABLED) {
    searchMgmtService.updateAlgoliaDocument(JSONs.clone(article));
  }
  if (Symphonys.ES_ENABLED) {
    searchMgmtService.updateESDocument(JSONs.clone(article),Article.ARTICLE);
  }
}
