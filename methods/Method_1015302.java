public static void notify(final String title,final CharSequence text){
  com.intellij.notification.Notifications.Bus.register("SmartIM",NotificationDisplayType.BALLOON);
  Notification n=new Notification("SmartIM",title,StringUtils.isEmpty(text) ? "" : text.toString(),NotificationType.INFORMATION);
  com.intellij.notification.Notifications.Bus.notify(n);
}
