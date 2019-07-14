public static void setUseThreeLinesLayout(boolean value){
  useThreeLinesLayout=value;
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("useThreeLinesLayout",useThreeLinesLayout);
  editor.commit();
  NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.dialogsNeedReload,true);
}
