private void startProjectActivity(final @NonNull Uri uri){
  final Intent projectIntent=new Intent(this,ProjectActivity.class).setData(uri);
  final String ref=uri.getQueryParameter("ref");
  if (ref != null) {
    projectIntent.putExtra(IntentKey.REF_TAG,RefTag.from(ref));
  }
  startActivity(projectIntent);
  finish();
}
