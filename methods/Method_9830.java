/** 
 * Fills notification count.
 * @param userId    the specified user id
 * @param dataModel the specified data model
 */
private void fillNotificationCount(final String userId,final Map<String,Object> dataModel){
  final int unreadCommentedNotificationCnt=notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_COMMENTED);
  dataModel.put(Common.UNREAD_COMMENTED_NOTIFICATION_CNT,unreadCommentedNotificationCnt);
  final int unreadReplyNotificationCnt=notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_REPLY);
  dataModel.put(Common.UNREAD_REPLY_NOTIFICATION_CNT,unreadReplyNotificationCnt);
  final int unreadAtNotificationCnt=notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_AT) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_ARTICLE_NEW_FOLLOWER) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_ARTICLE_NEW_WATCHER) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_COMMENT_VOTE_UP) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_COMMENT_VOTE_DOWN) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_ARTICLE_VOTE_UP) + notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_ARTICLE_VOTE_DOWN);
  dataModel.put(Common.UNREAD_AT_NOTIFICATION_CNT,unreadAtNotificationCnt);
  final int unreadFollowingNotificationCnt=notificationQueryService.getUnreadFollowingNotificationCount(userId);
  dataModel.put(Common.UNREAD_FOLLOWING_NOTIFICATION_CNT,unreadFollowingNotificationCnt);
  final int unreadPointNotificationCnt=notificationQueryService.getUnreadPointNotificationCount(userId);
  dataModel.put(Common.UNREAD_POINT_NOTIFICATION_CNT,unreadPointNotificationCnt);
  final int unreadBroadcastNotificationCnt=notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_BROADCAST);
  dataModel.put(Common.UNREAD_BROADCAST_NOTIFICATION_CNT,unreadBroadcastNotificationCnt);
  final int unreadSysAnnounceNotificationCnt=notificationQueryService.getUnreadSysAnnounceNotificationCount(userId);
  dataModel.put(Common.UNREAD_SYS_ANNOUNCE_NOTIFICATION_CNT,unreadSysAnnounceNotificationCnt);
  final int unreadNewFollowerNotificationCnt=notificationQueryService.getUnreadNotificationCountByType(userId,Notification.DATA_TYPE_C_NEW_FOLLOWER);
  dataModel.put(Common.UNREAD_NEW_FOLLOWER_NOTIFICATION_CNT,unreadNewFollowerNotificationCnt);
  dataModel.put(Common.UNREAD_NOTIFICATION_CNT,unreadAtNotificationCnt + unreadBroadcastNotificationCnt + unreadCommentedNotificationCnt + unreadFollowingNotificationCnt + unreadPointNotificationCnt + unreadReplyNotificationCnt + unreadSysAnnounceNotificationCnt + unreadNewFollowerNotificationCnt);
}
