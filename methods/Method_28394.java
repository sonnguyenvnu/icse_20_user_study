public static Intent createIntent(@NonNull Context context,@NonNull String login,@NonNull String repoId,boolean isEnterprise){
  Intent intent=new Intent(context,SearchFileActivity.class);
  intent.putExtra(BundleConstant.ID,repoId);
  intent.putExtra(BundleConstant.EXTRA,login);
  intent.putExtra(BundleConstant.IS_ENTERPRISE,isEnterprise);
  return intent;
}
