/** 
 * Gets a user's emotion (emoji with type=0).
 * @param userId the specified user id
 * @return emoji string join with {@code ","}, returns  {@code null} if not found
 * @throws RepositoryException repository exception
 */
public String getUserEmojis(final String userId) throws RepositoryException {
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Emotion.EMOTION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Emotion.EMOTION_TYPE,FilterOperator.EQUAL,Emotion.EMOTION_TYPE_C_EMOJI))).addSort(Emotion.EMOTION_SORT,SortDirection.ASCENDING);
  final JSONObject result=get(query);
  final JSONArray array=result.optJSONArray(Keys.RESULTS);
  if (0 == array.length()) {
    return null;
  }
  final StringBuilder retBuilder=new StringBuilder();
  for (int i=0; i < array.length(); i++) {
    retBuilder.append(array.optJSONObject(i).optString(Emotion.EMOTION_CONTENT));
    if (i != array.length() - 1) {
      retBuilder.append(",");
    }
  }
  return retBuilder.toString();
}
