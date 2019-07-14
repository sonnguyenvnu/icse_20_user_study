private void startProjectActivity(final @NonNull Project project,final @NonNull RefTag refTag){
  final Intent intent=new Intent(this.context(),ProjectActivity.class).putExtra(IntentKey.PROJECT,project).putExtra(IntentKey.REF_TAG,refTag);
  this.context().startActivity(intent);
}
