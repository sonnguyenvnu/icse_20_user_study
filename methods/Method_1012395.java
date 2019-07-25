public static void start(Context context){
  if (!is_lock) {
    Intent lockScreenIntent=new Intent(context,LockScreenActivity.class);
    lockScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(lockScreenIntent);
  }
}
