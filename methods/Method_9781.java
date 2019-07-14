/** 
 * Fills the domains with tags.
 * @param dataModel the specified data model
 */
private void fillDomainsWithTags(final Map<String,Object> dataModel){
  final List<JSONObject> domains=domainQueryService.getAllDomains();
  dataModel.put(Common.ADD_ARTICLE_DOMAINS,domains);
  for (  final JSONObject domain : domains) {
    final List<JSONObject> tags=domainQueryService.getTags(domain.optString(Keys.OBJECT_ID));
    domain.put(Domain.DOMAIN_T_TAGS,(Object)tags);
  }
  final JSONObject user=Sessions.getUser();
  if (null == user) {
    return;
  }
  try {
    final JSONObject followingTagsResult=followQueryService.getFollowingTags(user.optString(Keys.OBJECT_ID),1,28);
    final List<JSONObject> followingTags=(List<JSONObject>)followingTagsResult.opt(Keys.RESULTS);
    if (!followingTags.isEmpty()) {
      final JSONObject userWatched=new JSONObject();
      userWatched.put(Keys.OBJECT_ID,String.valueOf(System.currentTimeMillis()));
      userWatched.put(Domain.DOMAIN_TITLE,langPropsService.get("notificationFollowingLabel"));
      userWatched.put(Domain.DOMAIN_T_TAGS,(Object)followingTags);
      domains.add(0,userWatched);
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Get user [name=" + user.optString(User.USER_NAME) + "] following tags failed",e);
  }
}
