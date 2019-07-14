@Override public boolean onStartJob(JobParameters job){
  if (!SINGLE_JOB_ID.equalsIgnoreCase(job.getTag())) {
    if (PrefGetter.getNotificationTaskDuration() == -1) {
      scheduleJob(this,-1,false);
      finishJob(job);
      return true;
    }
  }
  Login login=null;
  try {
    login=Login.getUser();
  }
 catch (  Exception ignored) {
  }
  if (login != null) {
    RestProvider.getNotificationService(PrefGetter.isEnterprise()).getNotifications(ParseDateFormat.getLastWeekDate()).subscribeOn(Schedulers.io()).subscribe(item -> {
      AppHelper.cancelAllNotifications(getApplicationContext());
      if (item != null) {
        onSave(item.getItems(),job);
      }
 else {
        finishJob(job);
      }
    }
,throwable -> jobFinished(job,true));
  }
  return true;
}
