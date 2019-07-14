@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  if (Latkes.RuntimeMode.PRODUCTION != Latkes.getRuntimeMode() || StringUtils.isBlank(Symphonys.BAIDU_DATA_TOKEN)) {
    return;
  }
  try {
    final JSONObject article=data.getJSONObject(Article.ARTICLE);
    final int articleType=article.optInt(Article.ARTICLE_TYPE);
    if (Article.ARTICLE_TYPE_C_DISCUSSION == articleType || Article.ARTICLE_TYPE_C_THOUGHT == articleType) {
      return;
    }
    final String tags=article.optString(Article.ARTICLE_TAGS);
    if (StringUtils.containsIgnoreCase(tags,Tag.TAG_TITLE_C_SANDBOX)) {
      return;
    }
    final String articlePermalink=Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK);
    sendToBaidu(articlePermalink);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Sends the article to Baidu error",e);
  }
}
