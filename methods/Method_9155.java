public static void startCall(TLRPC.User user,final Activity activity,TLRPC.UserFull userFull){
  if (userFull != null && userFull.phone_calls_private) {
    new AlertDialog.Builder(activity).setTitle(LocaleController.getString("VoipFailed",R.string.VoipFailed)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("CallNotAvailable",R.string.CallNotAvailable,ContactsController.formatName(user.first_name,user.last_name)))).setPositiveButton(LocaleController.getString("OK",R.string.OK),null).show();
    return;
  }
  if (ConnectionsManager.getInstance(UserConfig.selectedAccount).getConnectionState() != ConnectionsManager.ConnectionStateConnected) {
    boolean isAirplaneMode=Settings.System.getInt(activity.getContentResolver(),Settings.System.AIRPLANE_MODE_ON,0) != 0;
    AlertDialog.Builder bldr=new AlertDialog.Builder(activity).setTitle(isAirplaneMode ? LocaleController.getString("VoipOfflineAirplaneTitle",R.string.VoipOfflineAirplaneTitle) : LocaleController.getString("VoipOfflineTitle",R.string.VoipOfflineTitle)).setMessage(isAirplaneMode ? LocaleController.getString("VoipOfflineAirplane",R.string.VoipOfflineAirplane) : LocaleController.getString("VoipOffline",R.string.VoipOffline)).setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
    if (isAirplaneMode) {
      final Intent settingsIntent=new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
      if (settingsIntent.resolveActivity(activity.getPackageManager()) != null) {
        bldr.setNeutralButton(LocaleController.getString("VoipOfflineOpenSettings",R.string.VoipOfflineOpenSettings),new DialogInterface.OnClickListener(){
          @Override public void onClick(          DialogInterface dialog,          int which){
            activity.startActivity(settingsIntent);
          }
        }
);
      }
    }
    bldr.show();
    return;
  }
  if (Build.VERSION.SDK_INT >= 23 && activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
    activity.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},101);
  }
 else {
    initiateCall(user,activity);
  }
}
