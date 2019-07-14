public static Intent createIntent(@NonNull Context context,@NonNull String login,boolean isOrg,boolean isEnterprise,int index){
  Intent intent=new Intent(context,UserPagerActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,login).put(BundleConstant.IS_ENTERPRISE,isEnterprise).put(BundleConstant.EXTRA_TYPE,isOrg).put(BundleConstant.EXTRA_TWO,index).end());
  if (context instanceof Service || context instanceof Application) {
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  }
  return intent;
}
