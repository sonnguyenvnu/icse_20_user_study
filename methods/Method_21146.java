private void startBackingActivity(final @NonNull Pair<Project,User> projectAndBacker){
  final Intent intent=new Intent(this,BackingActivity.class).putExtra(IntentKey.PROJECT,projectAndBacker.first).putExtra(IntentKey.BACKER,projectAndBacker.second).putExtra(IntentKey.IS_FROM_MESSAGES_ACTIVITY,true);
  startActivityWithTransition(intent,R.anim.slide_in_right,R.anim.fade_out_slide_out_left);
}
