public static Dialog createPopupSelectDialog(Activity parentActivity,final int globalType,final Runnable onSelect){
  SharedPreferences preferences=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
  final int[] selected=new int[1];
  if (globalType == NotificationsController.TYPE_PRIVATE) {
    selected[0]=preferences.getInt("popupAll",0);
  }
 else   if (globalType == NotificationsController.TYPE_GROUP) {
    selected[0]=preferences.getInt("popupGroup",0);
  }
 else {
    selected[0]=preferences.getInt("popupChannel",0);
  }
  String[] descriptions=new String[]{LocaleController.getString("NoPopup",R.string.NoPopup),LocaleController.getString("OnlyWhenScreenOn",R.string.OnlyWhenScreenOn),LocaleController.getString("OnlyWhenScreenOff",R.string.OnlyWhenScreenOff),LocaleController.getString("AlwaysShowPopup",R.string.AlwaysShowPopup)};
  final LinearLayout linearLayout=new LinearLayout(parentActivity);
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  for (int a=0; a < descriptions.length; a++) {
    RadioColorCell cell=new RadioColorCell(parentActivity);
    cell.setTag(a);
    cell.setPadding(AndroidUtilities.dp(4),0,AndroidUtilities.dp(4),0);
    cell.setCheckColor(Theme.getColor(Theme.key_radioBackground),Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    cell.setTextAndValue(descriptions[a],selected[0] == a);
    linearLayout.addView(cell);
    cell.setOnClickListener(v -> {
      selected[0]=(Integer)v.getTag();
      final SharedPreferences preferences1=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
      SharedPreferences.Editor editor=preferences1.edit();
      if (globalType == NotificationsController.TYPE_PRIVATE) {
        editor.putInt("popupAll",selected[0]);
      }
 else       if (globalType == NotificationsController.TYPE_GROUP) {
        editor.putInt("popupGroup",selected[0]);
      }
 else {
        editor.putInt("popupChannel",selected[0]);
      }
      editor.commit();
      builder.getDismissRunnable().run();
      if (onSelect != null) {
        onSelect.run();
      }
    }
);
  }
  builder.setTitle(LocaleController.getString("PopupNotification",R.string.PopupNotification));
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  return builder.create();
}
