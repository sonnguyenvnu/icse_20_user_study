private static void loadEmoji(final int page,final int page2){
  try {
    float scale;
    int imageResize=1;
    if (AndroidUtilities.density <= 1.0f) {
      scale=2.0f;
      imageResize=2;
    }
 else     if (AndroidUtilities.density <= 1.5f) {
      scale=2.0f;
    }
 else     if (AndroidUtilities.density <= 2.0f) {
      scale=2.0f;
    }
 else {
      scale=2.0f;
    }
    String imageName;
    File imageFile;
    try {
      for (int a=12; a < 14; a++) {
        imageName=String.format(Locale.US,"v%d_emoji%.01fx_%d.png",a,scale,page);
        imageFile=ApplicationLoader.applicationContext.getFileStreamPath(imageName);
        if (imageFile.exists()) {
          imageFile.delete();
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    Bitmap bitmap=null;
    try {
      InputStream is=ApplicationLoader.applicationContext.getAssets().open("emoji/" + String.format(Locale.US,"v14_emoji%.01fx_%d_%d.png",scale,page,page2));
      BitmapFactory.Options opts=new BitmapFactory.Options();
      opts.inJustDecodeBounds=false;
      opts.inSampleSize=imageResize;
      if (Build.VERSION.SDK_INT >= 26) {
        opts.inPreferredConfig=Bitmap.Config.HARDWARE;
      }
      bitmap=BitmapFactory.decodeStream(is,null,opts);
      is.close();
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
    final Bitmap finalBitmap=bitmap;
    AndroidUtilities.runOnUIThread(() -> {
      emojiBmp[page][page2]=finalBitmap;
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.emojiDidLoad);
    }
);
  }
 catch (  Throwable x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Error loading emoji",x);
    }
  }
}
