public static void navigateUp(@NonNull Activity activity,@Nullable Bundle extras){
  Intent upIntent=NavUtils.getParentActivityIntent(activity);
  if (upIntent != null) {
    if (extras != null) {
      upIntent.putExtras(extras);
    }
    if (NavUtils.shouldUpRecreateTask(activity,upIntent)) {
      TaskStackBuilder.create(activity).addNextIntentWithParentStack(upIntent).startActivities();
    }
 else {
      upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      activity.startActivity(upIntent);
    }
  }
  activity.finish();
}
