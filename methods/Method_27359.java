private void onSave(@Nullable List<Notification> notificationThreadModels,JobParameters job){
  if (notificationThreadModels != null) {
    Notification.save(notificationThreadModels);
    onNotifyUser(notificationThreadModels,job);
  }
}
