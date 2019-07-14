/** 
 * Generates article's audio.
 * @param article the specified article
 */
public void genArticleAudio(final JSONObject article){
  if (Article.ARTICLE_TYPE_C_THOUGHT == article.optInt(Article.ARTICLE_TYPE) || Article.ARTICLE_TYPE_C_DISCUSSION == article.optInt(Article.ARTICLE_TYPE)) {
    return;
  }
  final String tags=article.optString(Article.ARTICLE_TAGS);
  if (StringUtils.containsIgnoreCase(tags,Tag.TAG_TITLE_C_SANDBOX)) {
    return;
  }
  final String articleId=article.optString(Keys.OBJECT_ID);
  String previewContent=article.optString(Article.ARTICLE_CONTENT);
  previewContent=Markdowns.toHTML(previewContent);
  final Document doc=Jsoup.parse(previewContent);
  final Elements elements=doc.select("a, img, iframe, object, video");
  for (  final Element element : elements) {
    element.remove();
  }
  previewContent=Emotions.clear(doc.text());
  previewContent=StringUtils.substring(previewContent,0,512);
  final String contentToTTS=previewContent;
  final String authorId=article.optString(Article.ARTICLE_AUTHOR_ID);
  Symphonys.EXECUTOR_SERVICE.submit(() -> {
    try {
      String audioURL="";
      if (StringUtils.length(contentToTTS) < 128 || Runes.getChinesePercent(contentToTTS) < 40) {
        LOGGER.trace("Content is too short to TTS [contentToTTS=" + contentToTTS + "]");
      }
 else {
        audioURL=audioMgmtService.tts(contentToTTS,Article.ARTICLE,articleId,authorId);
      }
      if (StringUtils.isBlank(audioURL)) {
        return;
      }
      article.put(Article.ARTICLE_AUDIO_URL,audioURL);
      final JSONObject toUpdate=articleRepository.get(articleId);
      toUpdate.put(Article.ARTICLE_AUDIO_URL,audioURL);
      final Transaction transaction=articleRepository.beginTransaction();
      articleRepository.update(articleId,toUpdate,Article.ARTICLE_AUDIO_URL);
      transaction.commit();
      LOGGER.debug("Generated article [id=" + articleId + "] audio");
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Updates article's audio URL failed",e);
    }
  }
);
}
