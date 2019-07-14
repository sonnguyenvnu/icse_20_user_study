private static Intent chooserIntent(@NonNull Context context,@NonNull Intent intent,@NonNull Uri uri){
  final PackageManager pm=context.getPackageManager();
  final List<ResolveInfo> activities=pm.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
  final ArrayList<Intent> chooserIntents=new ArrayList<>();
  final String ourPackageName=context.getPackageName();
  for (  ResolveInfo resInfo : activities) {
    ActivityInfo info=resInfo.activityInfo;
    if (!info.enabled || !info.exported) {
      continue;
    }
    if (info.packageName.equals(ourPackageName)) {
      continue;
    }
    Intent targetIntent=new Intent(intent);
    targetIntent.setPackage(info.packageName);
    targetIntent.setDataAndType(uri,intent.getType());
    chooserIntents.add(targetIntent);
  }
  if (chooserIntents.isEmpty()) {
    return null;
  }
  final Intent lastIntent=chooserIntents.remove(chooserIntents.size() - 1);
  if (chooserIntents.isEmpty()) {
    return lastIntent;
  }
  Intent chooserIntent=Intent.createChooser(lastIntent,null);
  chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,chooserIntents.toArray(new Intent[chooserIntents.size()]));
  return chooserIntent;
}
