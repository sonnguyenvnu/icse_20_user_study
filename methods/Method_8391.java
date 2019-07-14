public static Dialog createVibrationSelectDialog(Activity parentActivity,final long dialog_id,final String prefKeyPrefix,final Runnable onSelect){
  SharedPreferences preferences=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
  final int[] selected=new int[1];
  String[] descriptions;
  if (dialog_id != 0) {
    selected[0]=preferences.getInt(prefKeyPrefix + dialog_id,0);
    if (selected[0] == 3) {
      selected[0]=2;
    }
 else     if (selected[0] == 2) {
      selected[0]=3;
    }
    descriptions=new String[]{LocaleController.getString("VibrationDefault",R.string.VibrationDefault),LocaleController.getString("Short",R.string.Short),LocaleController.getString("Long",R.string.Long),LocaleController.getString("VibrationDisabled",R.string.VibrationDisabled)};
  }
 else {
    selected[0]=preferences.getInt(prefKeyPrefix,0);
    if (selected[0] == 0) {
      selected[0]=1;
    }
 else     if (selected[0] == 1) {
      selected[0]=2;
    }
 else     if (selected[0] == 2) {
      selected[0]=0;
    }
    descriptions=new String[]{LocaleController.getString("VibrationDisabled",R.string.VibrationDisabled),LocaleController.getString("VibrationDefault",R.string.VibrationDefault),LocaleController.getString("Short",R.string.Short),LocaleController.getString("Long",R.string.Long),LocaleController.getString("OnlyIfSilent",R.string.OnlyIfSilent)};
  }
  final LinearLayout linearLayout=new LinearLayout(parentActivity);
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  for (int a=0; a < descriptions.length; a++) {
    RadioColorCell cell=new RadioColorCell(parentActivity);
    cell.setPadding(AndroidUtilities.dp(4),0,AndroidUtilities.dp(4),0);
    cell.setTag(a);
    cell.setCheckColor(Theme.getColor(Theme.key_radioBackground),Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    cell.setTextAndValue(descriptions[a],selected[0] == a);
    linearLayout.addView(cell);
    cell.setOnClickListener(v -> {
      selected[0]=(Integer)v.getTag();
      final SharedPreferences preferences1=MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
      SharedPreferences.Editor editor=preferences1.edit();
      if (dialog_id != 0) {
        if (selected[0] == 0) {
          editor.putInt(prefKeyPrefix + dialog_id,0);
        }
 else         if (selected[0] == 1) {
          editor.putInt(prefKeyPrefix + dialog_id,1);
        }
 else         if (selected[0] == 2) {
          editor.putInt(prefKeyPrefix + dialog_id,3);
        }
 else         if (selected[0] == 3) {
          editor.putInt(prefKeyPrefix + dialog_id,2);
        }
      }
 else {
        if (selected[0] == 0) {
          editor.putInt(prefKeyPrefix,2);
        }
 else         if (selected[0] == 1) {
          editor.putInt(prefKeyPrefix,0);
        }
 else         if (selected[0] == 2) {
          editor.putInt(prefKeyPrefix,1);
        }
 else         if (selected[0] == 3) {
          editor.putInt(prefKeyPrefix,3);
        }
 else         if (selected[0] == 4) {
          editor.putInt(prefKeyPrefix,4);
        }
      }
      editor.commit();
      builder.getDismissRunnable().run();
      if (onSelect != null) {
        onSelect.run();
      }
    }
);
  }
  builder.setTitle(LocaleController.getString("Vibrate",R.string.Vibrate));
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  return builder.create();
}
