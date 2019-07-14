public static boolean canRateCall(TLRPC.TL_messageActionPhoneCall call){
  if (!(call.reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy) && !(call.reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed)) {
    SharedPreferences prefs=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
    Set<String> hashes=prefs.getStringSet("calls_access_hashes",(Set<String>)Collections.EMPTY_SET);
    for (    String hash : hashes) {
      String[] d=hash.split(" ");
      if (d.length < 2)       continue;
      if (d[0].equals(call.call_id + "")) {
        return true;
      }
    }
  }
  return false;
}
