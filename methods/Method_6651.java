private void processMediaObserver(Uri uri){
  Cursor cursor=null;
  try {
    Point size=AndroidUtilities.getRealScreenSize();
    cursor=ApplicationLoader.applicationContext.getContentResolver().query(uri,mediaProjections,null,null,"date_added DESC LIMIT 1");
    final ArrayList<Long> screenshotDates=new ArrayList<>();
    if (cursor != null) {
      while (cursor.moveToNext()) {
        String val="";
        String data=cursor.getString(0);
        String display_name=cursor.getString(1);
        String album_name=cursor.getString(2);
        long date=cursor.getLong(3);
        String title=cursor.getString(4);
        int photoW=cursor.getInt(5);
        int photoH=cursor.getInt(6);
        if (data != null && data.toLowerCase().contains("screenshot") || display_name != null && display_name.toLowerCase().contains("screenshot") || album_name != null && album_name.toLowerCase().contains("screenshot") || title != null && title.toLowerCase().contains("screenshot")) {
          try {
            if (photoW == 0 || photoH == 0) {
              BitmapFactory.Options bmOptions=new BitmapFactory.Options();
              bmOptions.inJustDecodeBounds=true;
              BitmapFactory.decodeFile(data,bmOptions);
              photoW=bmOptions.outWidth;
              photoH=bmOptions.outHeight;
            }
            if (photoW <= 0 || photoH <= 0 || (photoW == size.x && photoH == size.y || photoH == size.x && photoW == size.y)) {
              screenshotDates.add(date);
            }
          }
 catch (          Exception e) {
            screenshotDates.add(date);
          }
        }
      }
      cursor.close();
    }
    if (!screenshotDates.isEmpty()) {
      AndroidUtilities.runOnUIThread(() -> {
        NotificationCenter.getInstance(lastChatAccount).postNotificationName(NotificationCenter.screenshotTook);
        checkScreenshots(screenshotDates);
      }
);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (cursor != null) {
        cursor.close();
      }
    }
 catch (    Exception ignore) {
    }
  }
}
