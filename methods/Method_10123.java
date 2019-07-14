/** 
 * Resets unverified users.
 */
@Transactional public void resetUnverifiedUsers(){
  final Date now=new Date();
  final long yesterdayTime=DateUtils.addDays(now,-1).getTime();
  final List<Filter> filters=new ArrayList<>();
  filters.add(new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_NOT_VERIFIED));
  filters.add(new PropertyFilter(Keys.OBJECT_ID,FilterOperator.LESS_THAN_OR_EQUAL,yesterdayTime));
  filters.add(new PropertyFilter(User.USER_NAME,FilterOperator.NOT_EQUAL,UserExt.NULL_USER_NAME));
  final Query query=new Query().setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
  try {
    final JSONObject result=userRepository.get(query);
    final JSONArray users=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < users.length(); i++) {
      final JSONObject user=users.optJSONObject(i);
      user.put(User.USER_NAME,UserExt.NULL_USER_NAME);
      final String email=user.optString(User.USER_EMAIL);
      user.put(User.USER_EMAIL,"");
      final String id=user.optString(Keys.OBJECT_ID);
      userRepository.update(id,user);
      LOGGER.log(Level.INFO,"Reset unverified user [email=" + email + "]");
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Reset unverified users failed",e);
  }
}
