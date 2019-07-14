private void startProjectActivity(final @NonNull Project project){
  final Intent intent=new Intent(this,ProjectActivity.class).putExtra(IntentKey.PROJECT,project).putExtra(IntentKey.REF_TAG,RefTag.activity());
  startActivityWithTransition(intent,R.anim.slide_in_right,R.anim.fade_out_slide_out_left);
}
