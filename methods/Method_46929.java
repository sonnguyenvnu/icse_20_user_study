@Override public void progressResumed(){
  getNotificationCustomViewSmall().setProgressBar(R.id.notification_service_progressBar_small,100,Math.round(getProgressHandler().getPercentProgress()),false);
  getNotificationCustomViewBig().setProgressBar(R.id.notification_service_progressBar_big,100,Math.round(getProgressHandler().getPercentProgress()),false);
  getNotificationManager().notify(getNotificationId(),getNotificationBuilder().build());
}
