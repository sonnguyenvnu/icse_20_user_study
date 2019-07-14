public static void scheduleOneTimeJob(@NonNull Context context){
  if (AppHelper.isGoogleAvailable(context)) {
    FirebaseJobDispatcher dispatcher=new FirebaseJobDispatcher(new GooglePlayDriver(context));
    Job.Builder builder=dispatcher.newJobBuilder().setTag(SINGLE_JOB_ID).setReplaceCurrent(true).setRecurring(false).setTrigger(Trigger.executionWindow(30,60)).setConstraints(Constraint.ON_ANY_NETWORK).setService(NotificationSchedulerJobTask.class);
    dispatcher.mustSchedule(builder.build());
  }
}
