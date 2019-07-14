public static void updateNightMode(@NonNull Activity activity){
  syncDefaultNightMode();
  sActivityHelper.onActivityStarted(activity);
}
