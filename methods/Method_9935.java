/** 
 * Tags the specified article with the specified tag titles.
 * @param tagTitles the specified (new) tag titles
 * @param article   the specified article
 * @param author    the specified author
 * @throws RepositoryException repository exception
 */
private synchronized void tag(final String[] tagTitles,final JSONObject article,final JSONObject author) throws RepositoryException {
  String articleTags=article.optString(Article.ARTICLE_TAGS);
  for (  final String t : tagTitles) {
    final String tagTitle=t.trim();
    JSONObject tag=tagRepository.getByTitle(tagTitle);
    String tagId;
    int userTagType;
    final int articleCmtCnt=article.optInt(Article.ARTICLE_COMMENT_CNT);
    if (null == tag) {
      LOGGER.log(Level.TRACE,"Found a new tag [title={0}] in article [title={1}]",tagTitle,article.optString(Article.ARTICLE_TITLE));
      tag=new JSONObject();
      tag.put(Tag.TAG_TITLE,tagTitle);
      final String tagURI=URLs.encode(tagTitle);
      tag.put(Tag.TAG_URI,StringUtils.lowerCase(tagURI));
      tag.put(Tag.TAG_CSS,"");
      tag.put(Tag.TAG_REFERENCE_CNT,1);
      tag.put(Tag.TAG_COMMENT_CNT,articleCmtCnt);
      tag.put(Tag.TAG_FOLLOWER_CNT,0);
      tag.put(Tag.TAG_LINK_CNT,0);
      tag.put(Tag.TAG_DESCRIPTION,"");
      tag.put(Tag.TAG_ICON_PATH,"");
      tag.put(Tag.TAG_STATUS,0);
      tag.put(Tag.TAG_GOOD_CNT,0);
      tag.put(Tag.TAG_BAD_CNT,0);
      tag.put(Tag.TAG_SEO_TITLE,tagTitle);
      tag.put(Tag.TAG_SEO_KEYWORDS,tagTitle);
      tag.put(Tag.TAG_SEO_DESC,"");
      tag.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
      tag.put(Tag.TAG_AD,"");
      tag.put(Tag.TAG_SHOW_SIDE_AD,0);
      tagId=tagRepository.add(tag);
      tag.put(Keys.OBJECT_ID,tagId);
      userTagType=Tag.TAG_TYPE_C_CREATOR;
      final JSONObject tagCntOption=optionRepository.get(Option.ID_C_STATISTIC_TAG_COUNT);
      final int tagCnt=tagCntOption.optInt(Option.OPTION_VALUE);
      tagCntOption.put(Option.OPTION_VALUE,tagCnt + 1);
      optionRepository.update(Option.ID_C_STATISTIC_TAG_COUNT,tagCntOption);
      author.put(UserExt.USER_TAG_COUNT,author.optInt(UserExt.USER_TAG_COUNT) + 1);
    }
 else {
      tagId=tag.optString(Keys.OBJECT_ID);
      LOGGER.log(Level.TRACE,"Found a existing tag[title={0}, id={1}] in article[title={2}]",tag.optString(Tag.TAG_TITLE),tag.optString(Keys.OBJECT_ID),article.optString(Article.ARTICLE_TITLE));
      final JSONObject tagTmp=new JSONObject();
      tagTmp.put(Keys.OBJECT_ID,tagId);
      final String title=tag.optString(Tag.TAG_TITLE);
      tagTmp.put(Tag.TAG_TITLE,title);
      tagTmp.put(Tag.TAG_COMMENT_CNT,tag.optInt(Tag.TAG_COMMENT_CNT) + articleCmtCnt);
      tagTmp.put(Tag.TAG_STATUS,tag.optInt(Tag.TAG_STATUS));
      tagTmp.put(Tag.TAG_REFERENCE_CNT,tag.optInt(Tag.TAG_REFERENCE_CNT) + 1);
      tagTmp.put(Tag.TAG_FOLLOWER_CNT,tag.optInt(Tag.TAG_FOLLOWER_CNT));
      tagTmp.put(Tag.TAG_LINK_CNT,tag.optInt(Tag.TAG_LINK_CNT));
      tagTmp.put(Tag.TAG_DESCRIPTION,tag.optString(Tag.TAG_DESCRIPTION));
      tagTmp.put(Tag.TAG_ICON_PATH,tag.optString(Tag.TAG_ICON_PATH));
      tagTmp.put(Tag.TAG_GOOD_CNT,tag.optInt(Tag.TAG_GOOD_CNT));
      tagTmp.put(Tag.TAG_BAD_CNT,tag.optInt(Tag.TAG_BAD_CNT));
      tagTmp.put(Tag.TAG_SEO_DESC,tag.optString(Tag.TAG_SEO_DESC));
      tagTmp.put(Tag.TAG_SEO_KEYWORDS,tag.optString(Tag.TAG_SEO_KEYWORDS));
      tagTmp.put(Tag.TAG_SEO_TITLE,tag.optString(Tag.TAG_SEO_TITLE));
      tagTmp.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
      tagTmp.put(Tag.TAG_URI,tag.optString(Tag.TAG_URI));
      tagTmp.put(Tag.TAG_CSS,tag.optString(Tag.TAG_CSS));
      tagTmp.put(Tag.TAG_AD,tag.optString(Tag.TAG_AD));
      tagTmp.put(Tag.TAG_SHOW_SIDE_AD,tag.optInt(Tag.TAG_SHOW_SIDE_AD));
      tagRepository.update(tagId,tagTmp);
      userTagType=Tag.TAG_TYPE_C_ARTICLE;
    }
    final JSONObject tagArticleRelation=new JSONObject();
    tagArticleRelation.put(Tag.TAG + "_" + Keys.OBJECT_ID,tagId);
    tagArticleRelation.put(Article.ARTICLE + "_" + Keys.OBJECT_ID,article.optString(Keys.OBJECT_ID));
    tagArticleRelation.put(Article.ARTICLE_LATEST_CMT_TIME,article.optLong(Article.ARTICLE_LATEST_CMT_TIME));
    tagArticleRelation.put(Article.ARTICLE_COMMENT_CNT,article.optInt(Article.ARTICLE_COMMENT_CNT));
    tagArticleRelation.put(Article.REDDIT_SCORE,article.optDouble(Article.REDDIT_SCORE,0D));
    tagArticleRelation.put(Article.ARTICLE_PERFECT,article.optInt(Article.ARTICLE_PERFECT));
    tagArticleRepository.add(tagArticleRelation);
    final String authorId=article.optString(Article.ARTICLE_AUTHOR_ID);
    if (Tag.TAG_TYPE_C_ARTICLE == userTagType) {
      userTagRepository.removeByUserIdAndTagId(authorId,tagId,Tag.TAG_TYPE_C_ARTICLE);
    }
    final JSONObject userTagRelation=new JSONObject();
    userTagRelation.put(Tag.TAG + '_' + Keys.OBJECT_ID,tagId);
    if (Article.ARTICLE_ANONYMOUS_C_ANONYMOUS == article.optInt(Article.ARTICLE_ANONYMOUS)) {
      userTagRelation.put(User.USER + '_' + Keys.OBJECT_ID,"0");
    }
 else {
      userTagRelation.put(User.USER + '_' + Keys.OBJECT_ID,authorId);
    }
    userTagRelation.put(Common.TYPE,userTagType);
    userTagRepository.add(userTagRelation);
  }
  final String[] tags=articleTags.split(",");
  final StringBuilder builder=new StringBuilder();
  for (  final String tagTitle : tags) {
    final JSONObject tag=tagRepository.getByTitle(tagTitle);
    builder.append(tag.optString(Tag.TAG_TITLE)).append(",");
  }
  if (builder.length() > 0) {
    builder.deleteCharAt(builder.length() - 1);
  }
  article.put(Article.ARTICLE_TAGS,builder.toString());
}
