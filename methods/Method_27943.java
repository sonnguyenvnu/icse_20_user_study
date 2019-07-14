public static void createIntentForOffline(@NonNull Context context,@NonNull Commit commitModel){
  SchemeParser.launchUri(context,Uri.parse(commitModel.getHtmlUrl()));
}
