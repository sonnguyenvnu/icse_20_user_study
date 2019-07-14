private static void initiateCall(final TLRPC.User user,final Activity activity){
  if (activity == null || user == null) {
    return;
  }
  if (VoIPService.getSharedInstance() != null) {
    TLRPC.User callUser=VoIPService.getSharedInstance().getUser();
    if (callUser.id != user.id) {
      new AlertDialog.Builder(activity).setTitle(LocaleController.getString("VoipOngoingAlertTitle",R.string.VoipOngoingAlertTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("VoipOngoingAlert",R.string.VoipOngoingAlert,ContactsController.formatName(callUser.first_name,callUser.last_name),ContactsController.formatName(user.first_name,user.last_name)))).setPositiveButton(LocaleController.getString("OK",R.string.OK),new DialogInterface.OnClickListener(){
        @Override public void onClick(        DialogInterface dialog,        int which){
          if (VoIPService.getSharedInstance() != null) {
            VoIPService.getSharedInstance().hangUp(new Runnable(){
              @Override public void run(){
                doInitiateCall(user,activity);
              }
            }
);
          }
 else {
            doInitiateCall(user,activity);
          }
        }
      }
).setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null).show();
    }
 else {
      activity.startActivity(new Intent(activity,VoIPActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
  }
 else   if (VoIPService.callIShouldHavePutIntoIntent == null) {
    doInitiateCall(user,activity);
  }
}
