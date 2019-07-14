public static void loadWallpaper(){
  if (wallpaper != null) {
    return;
  }
  Utilities.searchQueue.postRunnable(() -> {
synchronized (wallpaperSync) {
      SharedPreferences preferences=MessagesController.getGlobalMainSettings();
      boolean overrideTheme=preferences.getBoolean("overrideThemeWallpaper",false);
      isWallpaperMotion=preferences.getBoolean("selectedBackgroundMotion",false);
      isPatternWallpaper=preferences.getLong("selectedPattern",0) != 0;
      if (!overrideTheme) {
        Integer backgroundColor=currentColors.get(key_chat_wallpaper);
        if (backgroundColor != null) {
          wallpaper=new ColorDrawable(backgroundColor);
          isCustomTheme=true;
        }
 else         if (themedWallpaperFileOffset > 0 && (currentTheme.pathToFile != null || currentTheme.assetName != null)) {
          FileInputStream stream=null;
          try {
            int currentPosition=0;
            File file;
            if (currentTheme.assetName != null) {
              file=Theme.getAssetFile(currentTheme.assetName);
            }
 else {
              file=new File(currentTheme.pathToFile);
            }
            stream=new FileInputStream(file);
            stream.getChannel().position(themedWallpaperFileOffset);
            Bitmap bitmap=BitmapFactory.decodeStream(stream);
            if (bitmap != null) {
              themedWallpaper=wallpaper=new BitmapDrawable(bitmap);
              isCustomTheme=true;
            }
          }
 catch (          Throwable e) {
            FileLog.e(e);
          }
 finally {
            try {
              if (stream != null) {
                stream.close();
              }
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
        }
      }
      if (wallpaper == null) {
        int selectedColor=0;
        try {
          long selectedBackground=getSelectedBackgroundId();
          long selectedPattern=preferences.getLong("selectedPattern",0);
          selectedColor=preferences.getInt("selectedColor",0);
          if (selectedBackground == DEFAULT_BACKGROUND_ID) {
            wallpaper=ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.background_hd);
            isCustomTheme=false;
          }
 else           if (selectedBackground == -1 || selectedBackground < -100 || selectedBackground > 0) {
            if (selectedColor != 0 && selectedPattern == 0) {
              wallpaper=new ColorDrawable(selectedColor);
            }
 else {
              File toFile=new File(ApplicationLoader.getFilesDirFixed(),"wallpaper.jpg");
              long len=toFile.length();
              if (toFile.exists()) {
                wallpaper=Drawable.createFromPath(toFile.getAbsolutePath());
                isCustomTheme=true;
              }
 else {
                wallpaper=ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.background_hd);
                isCustomTheme=false;
              }
            }
          }
        }
 catch (        Throwable throwable) {
        }
        if (wallpaper == null) {
          if (selectedColor == 0) {
            selectedColor=-2693905;
          }
          wallpaper=new ColorDrawable(selectedColor);
        }
      }
      calcBackgroundColor(wallpaper,1);
      AndroidUtilities.runOnUIThread(() -> {
        applyChatServiceMessageColor();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetNewWallpapper);
      }
);
    }
  }
);
}
