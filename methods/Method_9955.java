/** 
 * Processes the specified article content. <ul> <li>Generates &#64;username home URL</li> <li>Markdowns</li> <li>Generates secured article content</li> <li>Blocks the article if need</li> <li>Generates emotion images</li> <li>Generates article link with article id</li> <li>Generates article abstract (preview content)</li> <li>Generates article ToC</li> </ul>
 * @param article the specified article, for example,"articleTitle": "", ...., "author": {}
 */
public void processArticleContent(final JSONObject article){
  Stopwatchs.start("Process content");
  try {
    final JSONObject author=article.optJSONObject(Article.ARTICLE_T_AUTHOR);
    if (null != author && UserExt.USER_STATUS_C_INVALID == author.optInt(UserExt.USER_STATUS) || Article.ARTICLE_STATUS_C_INVALID == article.optInt(Article.ARTICLE_STATUS)) {
      article.put(Article.ARTICLE_TITLE,langPropsService.get("articleTitleBlockLabel"));
      article.put(Article.ARTICLE_T_TITLE_EMOJI,langPropsService.get("articleTitleBlockLabel"));
      article.put(Article.ARTICLE_T_TITLE_EMOJI_UNICODE,langPropsService.get("articleTitleBlockLabel"));
      article.put(Article.ARTICLE_CONTENT,langPropsService.get("articleContentBlockLabel"));
      article.put(Article.ARTICLE_T_PREVIEW_CONTENT,Jsoup.clean(langPropsService.get("articleContentBlockLabel"),Whitelist.none()));
      article.put(Article.ARTICLE_T_TOC,"");
      article.put(Article.ARTICLE_REWARD_CONTENT,"");
      article.put(Article.ARTICLE_REWARD_POINT,0);
      article.put(Article.ARTICLE_QNA_OFFER_POINT,0);
      return;
    }
    article.put(Article.ARTICLE_T_PREVIEW_CONTENT,article.optString(Article.ARTICLE_TITLE));
    String articleContent=article.optString(Article.ARTICLE_CONTENT);
    article.put(Common.DISCUSSION_VIEWABLE,true);
    final JSONObject currentUser=Sessions.getUser();
    final String currentUserName=null == currentUser ? "" : currentUser.optString(User.USER_NAME);
    final String currentRole=null == currentUser ? "" : currentUser.optString(User.USER_ROLE);
    final String authorName=article.optString(Article.ARTICLE_T_AUTHOR_NAME);
    final int articleType=article.optInt(Article.ARTICLE_TYPE);
    if (Article.ARTICLE_TYPE_C_DISCUSSION == articleType && !authorName.equals(currentUserName) && !Role.ROLE_ID_C_ADMIN.equals(currentRole)) {
      boolean invited=false;
      final Set<String> userNames=userQueryService.getUserNames(articleContent);
      for (      final String userName : userNames) {
        if (userName.equals(currentUserName)) {
          invited=true;
          break;
        }
      }
      if (!invited) {
        String blockContent=langPropsService.get("articleDiscussionLabel");
        blockContent=blockContent.replace("{user}",UserExt.getUserLink(authorName));
        article.put(Article.ARTICLE_CONTENT,blockContent);
        article.put(Common.DISCUSSION_VIEWABLE,false);
        article.put(Article.ARTICLE_REWARD_CONTENT,"");
        article.put(Article.ARTICLE_REWARD_POINT,0);
        article.put(Article.ARTICLE_QNA_OFFER_POINT,0);
        article.put(Article.ARTICLE_T_TOC,"");
        article.put(Article.ARTICLE_AUDIO_URL,"");
        return;
      }
    }
    if (Article.ARTICLE_TYPE_C_THOUGHT != articleType) {
      articleContent=shortLinkQueryService.linkArticle(articleContent);
      articleContent=Emotions.convert(articleContent);
      article.put(Article.ARTICLE_CONTENT,articleContent);
    }
    if (article.optInt(Article.ARTICLE_REWARD_POINT) > 0) {
      String rewardContent=article.optString(Article.ARTICLE_REWARD_CONTENT);
      rewardContent=shortLinkQueryService.linkArticle(rewardContent);
      rewardContent=Emotions.convert(rewardContent);
      article.put(Article.ARTICLE_REWARD_CONTENT,rewardContent);
    }
    markdown(article);
    articleContent=article.optString(Article.ARTICLE_CONTENT);
    if (Article.ARTICLE_TYPE_C_THOUGHT != articleType) {
      articleContent=MP3Players.render(articleContent);
      articleContent=VideoPlayers.render(articleContent);
    }
    article.put(Article.ARTICLE_CONTENT,articleContent);
    article.put(Article.ARTICLE_T_PREVIEW_CONTENT,getArticleMetaDesc(article));
    article.put(Article.ARTICLE_T_TOC,getArticleToC(article));
  }
  finally {
    Stopwatchs.end();
  }
}
