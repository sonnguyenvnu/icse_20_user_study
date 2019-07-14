public void checkInviteText(){
  SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
  inviteLink=preferences.getString("invitelink",null);
  int time=preferences.getInt("invitelinktime",0);
  if (!updatingInviteLink && (inviteLink == null || Math.abs(System.currentTimeMillis() / 1000 - time) >= 86400)) {
    updatingInviteLink=true;
    TLRPC.TL_help_getInviteText req=new TLRPC.TL_help_getInviteText();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (response != null) {
        final TLRPC.TL_help_inviteText res=(TLRPC.TL_help_inviteText)response;
        if (res.message.length() != 0) {
          AndroidUtilities.runOnUIThread(() -> {
            updatingInviteLink=false;
            SharedPreferences preferences1=MessagesController.getMainSettings(currentAccount);
            SharedPreferences.Editor editor=preferences1.edit();
            editor.putString("invitelink",inviteLink=res.message);
            editor.putInt("invitelinktime",(int)(System.currentTimeMillis() / 1000));
            editor.commit();
          }
);
        }
      }
    }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  }
}
