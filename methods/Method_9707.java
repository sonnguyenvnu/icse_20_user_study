@Override public void action(final Event<JSONObject> event){
  final JSONObject data=event.getData();
  LOGGER.log(Level.TRACE,"Processing an event [type={0}, data={1}]",event.getType(),data);
  final JSONObject originalArticle=data.optJSONObject(Article.ARTICLE);
  articleMgmtService.genArticleAudio(originalArticle);
}
