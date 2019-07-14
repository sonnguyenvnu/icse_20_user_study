/** 
 * Gets ToC of the specified article.
 * @param article the specified article
 * @return ToC
 */
private String getArticleToC(final JSONObject article){
  Stopwatchs.start("ToC");
  if (Article.ARTICLE_TYPE_C_THOUGHT == article.optInt(Article.ARTICLE_TYPE)) {
    return "";
  }
  try {
    final String content=article.optString(Article.ARTICLE_CONTENT);
    final Document doc=Jsoup.parse(content,StringUtils.EMPTY,Parser.htmlParser());
    doc.outputSettings().prettyPrint(false);
    final Elements hs=doc.select("h1, h2, h3, h4, h5");
    if (hs.size() < 3) {
      return "";
    }
    final StringBuilder listBuilder=new StringBuilder();
    listBuilder.append("<ul class=\"article-toc\">");
    for (int i=0; i < hs.size(); i++) {
      final Element element=hs.get(i);
      final String tagName=element.tagName().toLowerCase();
      final String text=element.text();
      final String id="toc_" + tagName + "_" + i;
      element.attr("id",id);
      listBuilder.append("<li class='toc-").append(tagName).append("'><a data-id=\"").append(id).append("\" href=\"javascript:Comment._bgFade($('#").append(id).append("'))\">").append(text).append("</a></li>");
    }
    listBuilder.append("</ul>");
    article.put(Article.ARTICLE_CONTENT,doc.select("body").html());
    return listBuilder.toString();
  }
  finally {
    Stopwatchs.end();
  }
}
