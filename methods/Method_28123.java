public static void startForResult(@NonNull Activity activity,@NonNull Intent intent,@NonNull View view){
  ActivityHelper.startReveal(activity,intent,view,BundleConstant.REQUEST_CODE);
}
