public static Intent createIntent(@NonNull Context context,@NonNull String repoId,@NonNull String login,@NonNull String sha,boolean showRepoBtn,boolean isEnterprise){
  Intent intent=new Intent(context,CommitPagerActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.ID,sha).put(BundleConstant.EXTRA,login).put(BundleConstant.EXTRA_TWO,repoId).put(BundleConstant.EXTRA_THREE,showRepoBtn).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
  return intent;
}
