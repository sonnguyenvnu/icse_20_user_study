/** 
 * Checks whether has sent a notification to a user specified by the given user id with the specified data id and data type.
 * @param userId               the given user id
 * @param dataId               the specified the specified data id
 * @param notificationDataType the specified notification data type
 * @return {@code ture} if sent, returns {@code false} otherwise
 */
public boolean hasSentByDataIdAndType(final String userId,final String dataId,final int notificationDataType){
  try {
    return 0 < count(new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Notification.NOTIFICATION_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(Notification.NOTIFICATION_DATA_ID,FilterOperator.EQUAL,dataId),new PropertyFilter(Notification.NOTIFICATION_DATA_TYPE,FilterOperator.EQUAL,notificationDataType))));
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Checks [" + notificationDataType + "] notification sent failed [userId=" + userId + ", dataId=" + dataId + "]",e);
    return false;
  }
}
