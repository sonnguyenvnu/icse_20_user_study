private void onNotifyUser(@NonNull List<Notification> notificationThreadModels,JobParameters job){
  long count=Stream.of(notificationThreadModels).filter(Notification::isUnread).count();
  if (count == 0) {
    AppHelper.cancelAllNotifications(getApplicationContext());
    finishJob(job);
    return;
  }
  Context context=getApplicationContext();
  int accentColor=ContextCompat.getColor(this,R.color.material_blue_700);
  Notification first=notificationThreadModels.get(0);
  Observable.fromIterable(notificationThreadModels).subscribeOn(Schedulers.io()).filter(notification -> notification.isUnread() && first.getId() != notification.getId() && !NotificationQueue.exists(notification.getId())).take(10).flatMap(notification -> {
    if (notification.getSubject() != null && notification.getSubject().getLatestCommentUrl() != null) {
      return RestProvider.getNotificationService(PrefGetter.isEnterprise()).getComment(notification.getSubject().getLatestCommentUrl()).subscribeOn(Schedulers.io());
    }
 else {
      return Observable.empty();
    }
  }
,(thread,comment) -> {
    CustomNotificationModel customNotificationModel=new CustomNotificationModel();
    String url;
    if (comment != null && comment.getUser() != null) {
      url=comment.getUser().getAvatarUrl();
      if (!InputHelper.isEmpty(thread.getSubject().getLatestCommentUrl())) {
        customNotificationModel.comment=comment;
        customNotificationModel.url=url;
      }
    }
    customNotificationModel.notification=thread;
    return customNotificationModel;
  }
).subscribeOn(Schedulers.io()).subscribe(custom -> {
    if (custom.comment != null) {
      getNotificationWithComment(context,accentColor,custom.notification,custom.comment,custom.url);
    }
 else {
      showNotificationWithoutComment(context,accentColor,custom.notification,custom.url);
    }
  }
,throwable -> finishJob(job),() -> {
    if (!NotificationQueue.exists(first.getId())) {
      android.app.Notification grouped=getSummaryGroupNotification(first,accentColor,notificationThreadModels.size() > 1);
      showNotification(first.getId(),grouped);
    }
    NotificationQueue.put(notificationThreadModels).subscribe(aBoolean -> {
    }
,Throwable::printStackTrace,() -> finishJob(job));
  }
);
}
