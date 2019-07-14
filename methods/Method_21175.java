private void startProjectActivity(final @NonNull Pair<Project,RefTag> projectAndRefTag){
  final Intent intent=new Intent(this,ProjectActivity.class).putExtra(IntentKey.PROJECT,projectAndRefTag.first).putExtra(IntentKey.REF_TAG,projectAndRefTag.second);
  startActivityWithTransition(intent,R.anim.slide_in_right,R.anim.fade_out_slide_out_left);
}
