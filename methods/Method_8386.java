public static Dialog createMuteAlert(Context context,final long dialog_id){
  if (context == null) {
    return null;
  }
  BottomSheet.Builder builder=new BottomSheet.Builder(context);
  builder.setTitle(LocaleController.getString("Notifications",R.string.Notifications));
  CharSequence[] items=new CharSequence[]{LocaleController.formatString("MuteFor",R.string.MuteFor,LocaleController.formatPluralString("Hours",1)),LocaleController.formatString("MuteFor",R.string.MuteFor,LocaleController.formatPluralString("Hours",8)),LocaleController.formatString("MuteFor",R.string.MuteFor,LocaleController.formatPluralString("Days",2)),LocaleController.getString("MuteDisable",R.string.MuteDisable)};
  builder.setItems(items,(dialogInterface,i) -> {
    int setting;
    if (i == 0) {
      setting=NotificationsController.SETTING_MUTE_HOUR;
    }
 else     if (i == 1) {
      setting=NotificationsController.SETTING_MUTE_8_HOURS;
    }
 else     if (i == 2) {
      setting=NotificationsController.SETTING_MUTE_2_DAYS;
    }
 else {
      setting=NotificationsController.SETTING_MUTE_FOREVER;
    }
    NotificationsController.getInstance(UserConfig.selectedAccount).setDialogNotificationsSettings(dialog_id,setting);
  }
);
  return builder.create();
}
