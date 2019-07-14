public static Intent getIntent(@NonNull Context context,@NonNull String username,@NonNull String repoId,long id,boolean isEnterprise){
  Intent intent=new Intent(context,ReleasesListActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.ID,repoId).put(BundleConstant.EXTRA,username).put(BundleConstant.EXTRA_TWO,id).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
  return intent;
}
