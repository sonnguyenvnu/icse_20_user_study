@NonNull public static Intent startForResult(@NonNull Activity activity){
  String login="k0shk0sh";
  String repoId="FastHub";
  Intent intent=new Intent(activity,CreateIssueActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,login).put(BundleConstant.ID,repoId).put(BundleConstant.EXTRA_TWO,true).end());
  return intent;
}
