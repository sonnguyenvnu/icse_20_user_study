public static void showRateAlert(Context context,TLRPC.TL_messageActionPhoneCall call){
  SharedPreferences prefs=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
  Set<String> hashes=prefs.getStringSet("calls_access_hashes",(Set<String>)Collections.EMPTY_SET);
  for (  String hash : hashes) {
    String[] d=hash.split(" ");
    if (d.length < 2)     continue;
    if (d[0].equals(call.call_id + "")) {
      try {
        long accessHash=Long.parseLong(d[1]);
        showRateAlert(context,null,call.call_id,accessHash,UserConfig.selectedAccount,true);
      }
 catch (      Exception x) {
      }
      return;
    }
  }
}
