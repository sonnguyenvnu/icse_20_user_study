/** 
 * Tags the specified user with the specified tag titles.
 * @param user the specified article
 * @throws RepositoryException repository exception
 */
private synchronized void tag(final JSONObject user) throws RepositoryException {
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(User.USER + '_' + Keys.OBJECT_ID,FilterOperator.EQUAL,user.optString(Keys.OBJECT_ID)));
  filters.add(new PropertyFilter(Common.TYPE,FilterOperator.EQUAL,Tag.TAG_TYPE_C_USER_SELF));
  final Query query=new Query();
  query.setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  final JSONArray results=userTagRepository.get(query).optJSONArray(Keys.RESULTS);
  for (int i=0; i < results.length(); i++) {
    final JSONObject rel=results.optJSONObject(i);
    final String id=rel.optString(Keys.OBJECT_ID);
    userTagRepository.remove(id);
  }
  String tagTitleStr=user.optString(UserExt.USER_TAGS);
  final String[] tagTitles=tagTitleStr.split(",");
  for (  final String title : tagTitles) {
    final String tagTitle=title.trim();
    JSONObject tag=tagRepository.getByTitle(tagTitle);
    String tagId;
    if (null == tag) {
      LOGGER.log(Level.TRACE,"Found a new tag[title={0}] in user [name={1}]",tagTitle,user.optString(User.USER_NAME));
      tag=new JSONObject();
      tag.put(Tag.TAG_TITLE,tagTitle);
      final String tagURI=URLs.encode(tagTitle);
      tag.put(Tag.TAG_URI,StringUtils.lowerCase(tagURI));
      tag.put(Tag.TAG_CSS,"");
      tag.put(Tag.TAG_REFERENCE_CNT,0);
      tag.put(Tag.TAG_COMMENT_CNT,0);
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
      final JSONObject tagCntOption=optionRepository.get(Option.ID_C_STATISTIC_TAG_COUNT);
      final int tagCnt=tagCntOption.optInt(Option.OPTION_VALUE);
      tagCntOption.put(Option.OPTION_VALUE,tagCnt + 1);
      optionRepository.update(Option.ID_C_STATISTIC_TAG_COUNT,tagCntOption);
      final JSONObject userTagRelation=new JSONObject();
      userTagRelation.put(Tag.TAG + '_' + Keys.OBJECT_ID,tagId);
      userTagRelation.put(User.USER + '_' + Keys.OBJECT_ID,user.optString(Keys.OBJECT_ID));
      userTagRelation.put(Common.TYPE,Tag.TAG_TYPE_C_CREATOR);
      userTagRepository.add(userTagRelation);
    }
 else {
      tagId=tag.optString(Keys.OBJECT_ID);
      LOGGER.log(Level.TRACE,"Found a existing tag[title={0}, id={1}] in user[name={2}]",tag.optString(Tag.TAG_TITLE),tag.optString(Keys.OBJECT_ID),user.optString(User.USER_NAME));
      tagTitleStr=tagTitleStr.replaceAll("(?i)" + Pattern.quote(tagTitle),tag.optString(Tag.TAG_TITLE));
    }
    final JSONObject userTagRelation=new JSONObject();
    userTagRelation.put(Tag.TAG + '_' + Keys.OBJECT_ID,tagId);
    userTagRelation.put(User.USER + '_' + Keys.OBJECT_ID,user.optString(Keys.OBJECT_ID));
    userTagRelation.put(Common.TYPE,Tag.TAG_TYPE_C_USER_SELF);
    userTagRepository.add(userTagRelation);
  }
  user.put(UserExt.USER_TAGS,tagTitleStr);
}
