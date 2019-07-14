private static void doInitiateCall(TLRPC.User user,Activity activity){
  if (activity == null || user == null) {
    return;
  }
  if (System.currentTimeMillis() - lastCallTime < 2000)   return;
  lastCallTime=System.currentTimeMillis();
  Intent intent=new Intent(activity,VoIPService.class);
  intent.putExtra("user_id",user.id);
  intent.putExtra("is_outgoing",true);
  intent.putExtra("start_incall_activity",true);
  intent.putExtra("account",UserConfig.selectedAccount);
  try {
    activity.startService(intent);
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
}
