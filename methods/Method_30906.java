public static boolean isIntentHandled(@NonNull Intent intent,@NonNull Context context){
  return intent.resolveActivity(context.getPackageManager()) != null;
}
