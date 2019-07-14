public static Intent createIntent(@NonNull Context context,@NonNull String url,@NonNull String htmlUrl){
  Intent intent=new Intent(context,CodeViewerActivity.class);
  boolean isEnterprise=LinkParserHelper.isEnterprise(htmlUrl);
  url=LinkParserHelper.getEnterpriseGistUrl(url,isEnterprise);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA_TWO,htmlUrl).put(BundleConstant.EXTRA,url).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
  return intent;
}
