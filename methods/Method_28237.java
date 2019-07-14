public static Intent createIntent(@NonNull Context context,@NonNull String repoId,@NonNull String login,int number,boolean showRepoBtn,boolean isEnterprise,long commentId){
  Intent intent=new Intent(context,PullRequestPagerActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.ID,number).put(BundleConstant.EXTRA,login).put(BundleConstant.EXTRA_TWO,repoId).put(BundleConstant.EXTRA_THREE,showRepoBtn).put(BundleConstant.IS_ENTERPRISE,isEnterprise).put(BundleConstant.EXTRA_SIX,commentId).end());
  return intent;
}
