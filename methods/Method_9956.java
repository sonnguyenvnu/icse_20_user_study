/** 
 * Markdowns the specified article content. <ul> <li>Markdowns article content/reward content</li> <li>Generates secured article content/reward content</li> </ul>
 * @param article the specified article content
 */
private void markdown(final JSONObject article){
  String content=article.optString(Article.ARTICLE_CONTENT);
  final int articleType=article.optInt(Article.ARTICLE_TYPE);
  if (Article.ARTICLE_TYPE_C_THOUGHT != articleType) {
    content=Markdowns.toHTML(content);
    content=Markdowns.clean(content,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK));
  }
 else {
    final Document.OutputSettings outputSettings=new Document.OutputSettings();
    outputSettings.prettyPrint(false);
    content=Jsoup.clean(content,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK),Whitelist.relaxed().addAttributes(":all","id","target","class").addTags("span","hr").addAttributes("iframe","src","width","height").addAttributes("audio","controls","src"),outputSettings);
    content=content.replace("\n","\\n").replace("'","\\'").replace("\"","\\\"");
  }
  article.put(Article.ARTICLE_CONTENT,content);
  if (article.optInt(Article.ARTICLE_REWARD_POINT) > 0) {
    String rewardContent=article.optString(Article.ARTICLE_REWARD_CONTENT);
    rewardContent=Markdowns.toHTML(rewardContent);
    rewardContent=Markdowns.clean(rewardContent,Latkes.getServePath() + article.optString(Article.ARTICLE_PERMALINK));
    article.put(Article.ARTICLE_REWARD_CONTENT,rewardContent);
  }
}
