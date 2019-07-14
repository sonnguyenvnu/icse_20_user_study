public static void scheduleJob(@NonNull Context context){
  int duration=PrefGetter.getNotificationTaskDuration();
  scheduleJob(context,duration,false);
}
