@Nullable private static Intent getGistFile(@NonNull Context context,@NonNull Uri uri){
  if (HOST_GISTS_RAW.equalsIgnoreCase(uri.getHost())) {
    return CodeViewerActivity.createIntent(context,uri.toString(),uri.toString());
  }
  return null;
}
