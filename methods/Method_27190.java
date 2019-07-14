public static ArrayList<NotificationSoundModel> getNotificationSounds(Context context,@Nullable String defaultValue){
  ArrayList<NotificationSoundModel> notificationSounds=new ArrayList<>();
  RingtoneManager ringtoneManager=new RingtoneManager(context);
  ringtoneManager.setType(RingtoneManager.TYPE_NOTIFICATION);
  try (Cursor ringsCursor=ringtoneManager.getCursor()){
    while (ringsCursor.moveToNext()) {
      String title=ringsCursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
      Uri uri=Uri.parse(ringsCursor.getString(RingtoneManager.URI_COLUMN_INDEX) + "/" + ringsCursor.getString(RingtoneManager.ID_COLUMN_INDEX));
      boolean selected=defaultValue != null && (uri.toString().contains(defaultValue) || title.equalsIgnoreCase(defaultValue) || defaultValue.contains(title));
      Logger.e(defaultValue,title,uri,selected);
      notificationSounds.add(new NotificationSoundModel(title,uri,selected));
    }
  }
   return notificationSounds;
}
