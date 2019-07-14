@NonNull public static Intent getIntent(@NonNull Context context,@NonNull String login,@NonNull String repoId,boolean isFeedback){
  Intent intent=new Intent(context,CreateIssueActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,login).put(BundleConstant.ID,repoId).put(BundleConstant.EXTRA_TWO,isFeedback).end());
  return intent;
}
