private void startProjectActivity(final @NonNull Project project,final @NonNull RefTag refTag){
  final Intent intent=new Intent(getActivity(),ProjectActivity.class).putExtra(IntentKey.PROJECT,project).putExtra(IntentKey.REF_TAG,refTag);
  startActivity(intent);
  transition(getActivity(),slideInFromRight());
}
