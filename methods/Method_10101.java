/** 
 * Processes article short link (article id).
 * @param content the specified content
 * @return processed content
 */
public String linkArticle(final String content){
  Stopwatchs.start("Link article");
  StringBuffer contentBuilder=new StringBuffer();
  try {
    Matcher matcher=ARTICLE_PATTERN_FULL.matcher(content);
    final String[] codeBlocks=StringUtils.substringsBetween(content,"```","```");
    String codes="";
    if (null != codeBlocks) {
      codes=String.join("",codeBlocks);
    }
    try {
      while (matcher.find()) {
        final String url=StringUtils.trim(matcher.group());
        if (0 < matcher.start()) {
          final char c=content.charAt(matcher.start() - 1);
          if ('(' == c || ']' == c || '\'' == c || '"' == c) {
            continue;
          }
        }
        if (StringUtils.containsIgnoreCase(codes,url)) {
          continue;
        }
        String linkId;
        String queryStr=null;
        String anchor=null;
        if (StringUtils.contains(url,"?")) {
          linkId=StringUtils.substringBetween(url,"/article/","?");
          queryStr=StringUtils.substringAfter(url,"?");
        }
 else {
          linkId=StringUtils.substringAfter(url,"/article/");
        }
        if (StringUtils.contains(url,"#")) {
          linkId=StringUtils.substringBefore(linkId,"#");
          anchor=StringUtils.substringAfter(url,"#");
        }
        final Query query=new Query().select(Article.ARTICLE_TITLE).setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.EQUAL,linkId));
        final JSONArray results=articleRepository.get(query).optJSONArray(Keys.RESULTS);
        if (0 == results.length()) {
          continue;
        }
        final JSONObject linkArticle=results.optJSONObject(0);
        final String linkTitle=linkArticle.optString(Article.ARTICLE_TITLE);
        String link=" [" + linkTitle + "](" + Latkes.getServePath() + "/article/" + linkId;
        if (StringUtils.isNotBlank(queryStr)) {
          link+="?" + queryStr;
        }
        if (StringUtils.isNotBlank(anchor)) {
          link+="#" + anchor;
        }
        link+=") ";
        matcher.appendReplacement(contentBuilder,link);
      }
      matcher.appendTail(contentBuilder);
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Generates article link error",e);
    }
    return contentBuilder.toString();
  }
  finally {
    Stopwatchs.end();
  }
}
