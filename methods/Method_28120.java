public static void startForResult(@NonNull Activity activity,@NonNull String login,@NonNull String repoId,@Nullable Issue issueModel,boolean isEnterprise){
  if (issueModel != null) {
    Intent intent=new Intent(activity,CreateIssueActivity.class);
    intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,login).put(BundleConstant.ID,repoId).put(BundleConstant.ITEM,issueModel).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
    View view=activity.findViewById(R.id.fab);
    if (view != null) {
      startForResult(activity,intent,view);
    }
 else {
      activity.startActivityForResult(intent,BundleConstant.REQUEST_CODE);
    }
  }
}
