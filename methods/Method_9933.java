/** 
 * Processes tags for article update. <p> <ul> <li>Un-tags old article, decrements tag reference count</li> <li>Removes old article-tag relations</li> <li>Saves new article-tag relations with tag reference count</li> </ul> </p>
 * @param oldArticle the specified old article
 * @param newArticle the specified new article
 * @param author     the specified author
 * @throws Exception exception
 */
private synchronized void processTagsForArticleUpdate(final JSONObject oldArticle,final JSONObject newArticle,final JSONObject author) throws Exception {
  final String oldArticleId=oldArticle.getString(Keys.OBJECT_ID);
  final List<JSONObject> oldTags=tagRepository.getByArticleId(oldArticleId);
  String tagsString=newArticle.getString(Article.ARTICLE_TAGS);
  tagsString=Tag.formatTags(tagsString);
  if (StringUtils.containsIgnoreCase(tagsString,Tag.TAG_TITLE_C_SANDBOX)) {
    tagsString=Tag.TAG_TITLE_C_SANDBOX;
  }
  final int articleType=newArticle.optInt(Article.ARTICLE_TYPE);
  if (StringUtils.isBlank(tagsString)) {
    tagsString="???";
  }
  tagsString=Tag.formatTags(tagsString);
  if (Article.ARTICLE_TYPE_C_QNA == articleType && !StringUtils.contains(tagsString,"Q&A")) {
    tagsString+=",Q&A";
  }
  newArticle.put(Article.ARTICLE_TAGS,tagsString);
  String[] tagStrings=tagsString.split(",");
  final List<JSONObject> newTags=new ArrayList<>();
  for (  final String tagString : tagStrings) {
    final String tagTitle=tagString.trim();
    JSONObject newTag=tagRepository.getByTitle(tagTitle);
    if (null == newTag) {
      newTag=new JSONObject();
      newTag.put(Tag.TAG_TITLE,tagTitle);
    }
    newTags.add(newTag);
  }
  final List<JSONObject> tagsDropped=new ArrayList<>();
  final List<JSONObject> tagsNeedToAdd=new ArrayList<>();
  for (  final JSONObject newTag : newTags) {
    final String newTagTitle=newTag.getString(Tag.TAG_TITLE);
    if (!tagExists(newTagTitle,oldTags)) {
      LOGGER.log(Level.DEBUG,"Tag need to add [title={0}]",newTagTitle);
      tagsNeedToAdd.add(newTag);
    }
  }
  for (  final JSONObject oldTag : oldTags) {
    final String oldTagTitle=oldTag.getString(Tag.TAG_TITLE);
    if (!tagExists(oldTagTitle,newTags)) {
      LOGGER.log(Level.DEBUG,"Tag dropped [title={0}]",oldTag);
      tagsDropped.add(oldTag);
    }
  }
  final int articleCmtCnt=oldArticle.getInt(Article.ARTICLE_COMMENT_CNT);
  for (  final JSONObject tagDropped : tagsDropped) {
    final String tagId=tagDropped.getString(Keys.OBJECT_ID);
    int refCnt=tagDropped.getInt(Tag.TAG_REFERENCE_CNT) - 1;
    refCnt=refCnt < 0 ? 0 : refCnt;
    tagDropped.put(Tag.TAG_REFERENCE_CNT,refCnt);
    final int tagCmtCnt=tagDropped.getInt(Tag.TAG_COMMENT_CNT);
    tagDropped.put(Tag.TAG_COMMENT_CNT,tagCmtCnt - articleCmtCnt);
    tagDropped.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
    tagRepository.update(tagId,tagDropped);
  }
  final String[] tagIdsDropped=new String[tagsDropped.size()];
  for (int i=0; i < tagIdsDropped.length; i++) {
    final JSONObject tag=tagsDropped.get(i);
    final String id=tag.getString(Keys.OBJECT_ID);
    tagIdsDropped[i]=id;
  }
  if (0 != tagIdsDropped.length) {
    removeTagArticleRelations(oldArticleId,tagIdsDropped);
    removeUserTagRelations(oldArticle.optString(Article.ARTICLE_AUTHOR_ID),Tag.TAG_TYPE_C_ARTICLE,tagIdsDropped);
  }
  tagStrings=new String[tagsNeedToAdd.size()];
  for (int i=0; i < tagStrings.length; i++) {
    final JSONObject tag=tagsNeedToAdd.get(i);
    final String tagTitle=tag.getString(Tag.TAG_TITLE);
    tagStrings[i]=tagTitle;
  }
  newArticle.put(Article.ARTICLE_COMMENT_CNT,articleCmtCnt);
  tag(tagStrings,newArticle,author);
}
