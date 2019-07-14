public static Intent createIntent(@NonNull Context context,@NonNull String gistId,boolean isEnterprise){
  Intent intent=new Intent(context,GistActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,gistId).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
  return intent;
}
