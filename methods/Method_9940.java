/** 
 * Gets the previous article.
 * @param articleId the specified article id
 * @return permalink and title, <pre>{ "articlePermalink": "", "articleTitle": "", "articleTitleEmoj": "", "articleTitleEmojUnicode": "" } </pre>, returns  {@code null} if not found
 */
public JSONObject getPreviousPermalink(final String articleId){
  Stopwatchs.start("Get previous");
  try {
    final Query query=new Query().setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN,articleId)).addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).select(Article.ARTICLE_PERMALINK,Article.ARTICLE_TITLE).setPage(1,1).setPageCount(1);
    final JSONArray result=articleRepository.get(query).optJSONArray(Keys.RESULTS);
    if (0 == result.length()) {
      return null;
    }
    final JSONObject ret=result.optJSONObject(0);
    if (null == ret) {
      return null;
    }
    final String title=Escapes.escapeHTML(ret.optString(Article.ARTICLE_TITLE));
    ret.put(Article.ARTICLE_T_TITLE_EMOJI,Emotions.convert(title));
    ret.put(Article.ARTICLE_T_TITLE_EMOJI_UNICODE,EmojiParser.parseToUnicode(title));
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets previous article permalink failed",e);
    return null;
  }
 finally {
    Stopwatchs.end();
  }
}
