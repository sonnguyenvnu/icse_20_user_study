/** 
 * Converts the specified article create/update/latest comment time (long) to date type and format str.
 * @param article the specified article
 */
private void toArticleDate(final JSONObject article){
  article.put(Common.TIME_AGO,Times.getTimeAgo(article.optLong(Article.ARTICLE_CREATE_TIME),Locales.getLocale()));
  article.put(Common.CMT_TIME_AGO,Times.getTimeAgo(article.optLong(Article.ARTICLE_LATEST_CMT_TIME),Locales.getLocale()));
  final Date createDate=new Date(article.optLong(Article.ARTICLE_CREATE_TIME));
  article.put(Article.ARTICLE_CREATE_TIME,createDate);
  article.put(Article.ARTICLE_CREATE_TIME_STR,DateFormatUtils.format(createDate,"yyyy-MM-dd HH:mm:ss"));
  final Date updateDate=new Date(article.optLong(Article.ARTICLE_UPDATE_TIME));
  article.put(Article.ARTICLE_UPDATE_TIME,updateDate);
  article.put(Article.ARTICLE_UPDATE_TIME_STR,DateFormatUtils.format(updateDate,"yyyy-MM-dd HH:mm:ss"));
  final Date latestCmtDate=new Date(article.optLong(Article.ARTICLE_LATEST_CMT_TIME));
  article.put(Article.ARTICLE_LATEST_CMT_TIME,latestCmtDate);
  article.put(Article.ARTICLE_LATEST_CMT_TIME_STR,DateFormatUtils.format(latestCmtDate,"yyyy-MM-dd HH:mm:ss"));
}
