public static void startActivity(@NonNull Context context,@NonNull String url,boolean isEnterprise){
  if (!InputHelper.isEmpty(url)) {
    Intent intent=ActivityHelper.editBundle(getIntent(context,url),isEnterprise);
    context.startActivity(intent);
  }
}
