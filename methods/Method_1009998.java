@Override public void unsubscribe(NotificationHolder notificationHolder){
  NotificationThread notification=notificationHolder.notification;
  NotificationService service=ServiceFactory.get(NotificationService.class,false);
  SubscriptionRequest request=SubscriptionRequest.builder().ignored(true).build();
  service.setNotificationThreadSubscription(notification.id(),request).map(ApiHelpers::throwOnFailure).compose(RxUtils::doInBackground).subscribe(result -> handleMarkAsRead(null,notification));
}
