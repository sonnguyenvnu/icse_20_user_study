/** 
 * Loads all usernames from database.
 */
public void loadUserNames(){
  USER_NAMES.clear();
  final Query query=new Query().setPageCount(1).setFilter(new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_VALID)).select(User.USER_NAME,UserExt.USER_AVATAR_URL);
  try {
    final JSONObject result=userRepository.get(query);
    final JSONArray array=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < array.length(); i++) {
      final JSONObject user=array.optJSONObject(i);
      final JSONObject u=new JSONObject();
      u.put(User.USER_NAME,user.optString(User.USER_NAME));
      u.put(UserExt.USER_T_NAME_LOWER_CASE,user.optString(User.USER_NAME).toLowerCase());
      final String avatar=avatarQueryService.getAvatarURLByUser(user,"20");
      u.put(UserExt.USER_AVATAR_URL,avatar);
      USER_NAMES.add(u);
    }
    Collections.sort(USER_NAMES,(u1,u2) -> {
      final String u1Name=u1.optString(UserExt.USER_T_NAME_LOWER_CASE);
      final String u2Name=u2.optString(UserExt.USER_T_NAME_LOWER_CASE);
      return u1Name.compareTo(u2Name);
    }
);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Loads usernames error",e);
  }
}
