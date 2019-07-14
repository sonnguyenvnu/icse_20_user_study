/** 
 * Gets meta description content of the specified article.
 * @param article the specified article
 * @return meta description
 */
String getArticleMetaDesc(final JSONObject article){
  final String articleId=article.optString(Keys.OBJECT_ID);
  String articleAbstract=articleCache.getArticleAbstract(articleId);
  if (StringUtils.isNotBlank(articleAbstract)) {
    return articleAbstract;
  }
  Stopwatchs.start("Meta Desc");
  try {
    final int articleType=article.optInt(Article.ARTICLE_TYPE);
    if (Article.ARTICLE_TYPE_C_THOUGHT == articleType) {
      return "....";
    }
    if (Article.ARTICLE_TYPE_C_DISCUSSION == articleType) {
      return langPropsService.get("articleAbstractDiscussionLabel",Latkes.getLocale());
    }
    final int length=Integer.valueOf("150");
    String ret=article.optString(Article.ARTICLE_CONTENT);
    ret=Emotions.clear(ret);
    try {
      ret=Markdowns.toHTML(ret);
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Parses article abstract failed [id=" + articleId + ", md=" + ret + "]");
      throw e;
    }
    final Whitelist whitelist=Whitelist.basicWithImages();
    whitelist.addTags("object","video");
    ret=Jsoup.clean(ret,whitelist);
    final int threshold=20;
    String[] pics=StringUtils.substringsBetween(ret,"<img",">");
    if (null != pics) {
      if (pics.length > threshold) {
        pics=Arrays.copyOf(pics,threshold);
      }
      final String[] picsRepl=new String[pics.length];
      for (int i=0; i < picsRepl.length; i++) {
        picsRepl[i]=langPropsService.get("picTagLabel",Latkes.getLocale());
        pics[i]="<img" + pics[i] + ">";
        if (i > threshold) {
          break;
        }
      }
      ret=StringUtils.replaceEach(ret,pics,picsRepl);
    }
    String[] objs=StringUtils.substringsBetween(ret,"<object>","</object>");
    if (null != objs) {
      if (objs.length > threshold) {
        objs=Arrays.copyOf(objs,threshold);
      }
      final String[] objsRepl=new String[objs.length];
      for (int i=0; i < objsRepl.length; i++) {
        objsRepl[i]=langPropsService.get("objTagLabel",Latkes.getLocale());
        objs[i]="<object>" + objs[i] + "</object>";
        if (i > threshold) {
          break;
        }
      }
      ret=StringUtils.replaceEach(ret,objs,objsRepl);
    }
    objs=StringUtils.substringsBetween(ret,"<video","</video>");
    if (null != objs) {
      if (objs.length > threshold) {
        objs=Arrays.copyOf(objs,threshold);
      }
      final String[] objsRepl=new String[objs.length];
      for (int i=0; i < objsRepl.length; i++) {
        objsRepl[i]=langPropsService.get("objTagLabel",Latkes.getLocale());
        objs[i]="<video" + objs[i] + "</video>";
        if (i > threshold) {
          break;
        }
      }
      ret=StringUtils.replaceEach(ret,objs,objsRepl);
    }
    String tmp=Jsoup.clean(Jsoup.parse(ret).text(),Whitelist.none());
    if (tmp.length() >= length && null != pics) {
      tmp=StringUtils.substring(tmp,0,length) + " ....";
      ret=tmp.replaceAll("\"","'");
      articleCache.putArticleAbstract(articleId,ret);
      return ret;
    }
    String[] urls=StringUtils.substringsBetween(ret,"<a","</a>");
    if (null != urls) {
      if (urls.length > threshold) {
        urls=Arrays.copyOf(urls,threshold);
      }
      final String[] urlsRepl=new String[urls.length];
      for (int i=0; i < urlsRepl.length; i++) {
        urlsRepl[i]=langPropsService.get("urlTagLabel",Latkes.getLocale());
        urls[i]="<a" + urls[i] + "</a>";
      }
      ret=StringUtils.replaceEach(ret,urls,urlsRepl);
    }
    tmp=Jsoup.clean(Jsoup.parse(ret).text(),Whitelist.none());
    if (tmp.length() >= length) {
      tmp=StringUtils.substring(tmp,0,length) + " ....";
    }
    ret=tmp.replaceAll("\"","'");
    articleCache.putArticleAbstract(articleId,ret);
    return ret;
  }
  finally {
    Stopwatchs.end();
  }
}
