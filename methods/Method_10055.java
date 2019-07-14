/** 
 * Makes the specified user to comments notifications as read.
 * @param userId     the specified user id
 * @param commentIds the specified comment ids
 */
public void makeRead(final String userId,final List<String> commentIds){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Notification.NOTIFICATION_HAS_READ,FilterOperator.EQUAL,false),new PropertyFilter(Notification.NOTIFICATION_DATA_ID,FilterOperator.IN,commentIds)));
  try {
    final Set<JSONObject> notifications=CollectionUtils.jsonArrayToSet(notificationRepository.get(query).optJSONArray(Keys.RESULTS));
    makeRead(notifications);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Makes read failed",e);
  }
}
