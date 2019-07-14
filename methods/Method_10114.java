/** 
 * Gets the participants (article ref) of the specified tag of the given tag id.
 * @param tagId     the given tag id
 * @param fetchSize the specified fetch size
 * @return tag participants, for example,      <pre>[ { "tagParticipantName": "", "tagParticipantThumbnailURL": "", "tagParticipantThumbnailUpdateTime": long }, .... ] </pre>, returns an empty list if not found
 */
public List<JSONObject> getParticipants(final String tagId,final int fetchSize){
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(Tag.TAG + '_' + Keys.OBJECT_ID,FilterOperator.EQUAL,tagId));
  filters.add(new PropertyFilter(Common.TYPE,FilterOperator.EQUAL,1));
  Query query=new Query().setPage(1,fetchSize).setPageCount(1).setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  final List<JSONObject> ret=new ArrayList<>();
  try {
    JSONObject result=userTagRepository.get(query);
    final JSONArray userTagRelations=result.optJSONArray(Keys.RESULTS);
    final Set<String> userIds=new HashSet<>();
    for (int i=0; i < userTagRelations.length(); i++) {
      userIds.add(userTagRelations.optJSONObject(i).optString(User.USER + '_' + Keys.OBJECT_ID));
    }
    query=new Query().setFilter(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.IN,userIds));
    result=userRepository.get(query);
    final List<JSONObject> users=CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
    for (    final JSONObject user : users) {
      final JSONObject participant=new JSONObject();
      participant.put(Tag.TAG_T_PARTICIPANT_NAME,user.optString(User.USER_NAME));
      final String thumbnailURL=avatarQueryService.getAvatarURLByUser(user,"48");
      participant.put(Tag.TAG_T_PARTICIPANT_THUMBNAIL_URL,thumbnailURL);
      participant.put(Tag.TAG_T_PARTICIPANT_THUMBNAIL_UPDATE_TIME,user.optLong(UserExt.USER_UPDATE_TIME));
      ret.add(participant);
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets tag participants failed",e);
    return Collections.emptyList();
  }
}
