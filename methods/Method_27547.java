public static void startActivity(@NonNull Context context,@NonNull String url,@NonNull String htmlUrl){
  if (!InputHelper.isEmpty(url)) {
    Intent intent=ActivityHelper.editBundle(createIntent(context,url,htmlUrl),LinkParserHelper.isEnterprise(htmlUrl));
    context.startActivity(intent);
  }
}
