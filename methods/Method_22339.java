@NonNull private List<Intent> buildInitialIntents(@NonNull PackageManager pm,@NonNull Intent resolveIntent,@NonNull Intent emailIntent){
  final List<ResolveInfo> resolveInfoList=pm.queryIntentActivities(resolveIntent,PackageManager.MATCH_DEFAULT_ONLY);
  final List<Intent> initialIntents=new ArrayList<>();
  for (  ResolveInfo info : resolveInfoList) {
    final Intent packageSpecificIntent=new Intent(emailIntent);
    packageSpecificIntent.setPackage(info.activityInfo.packageName);
    if (packageSpecificIntent.resolveActivity(pm) != null) {
      initialIntents.add(packageSpecificIntent);
    }
  }
  return initialIntents;
}
