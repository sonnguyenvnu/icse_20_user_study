@Nullable public static Intent makeLaunchApp(@NonNull String packageName,@NonNull Context context){
  return context.getPackageManager().getLaunchIntentForPackage(packageName);
}
