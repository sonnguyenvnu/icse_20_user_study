public static void checkDisplaySize(Context context,Configuration newConfiguration){
  try {
    int oldDensity=(int)density;
    density=context.getResources().getDisplayMetrics().density;
    int newDensity=(int)density;
    if (firstConfigurationWas && oldDensity != newDensity) {
      Theme.reloadAllResources(context);
    }
    firstConfigurationWas=true;
    Configuration configuration=newConfiguration;
    if (configuration == null) {
      configuration=context.getResources().getConfiguration();
    }
    usingHardwareInput=configuration.keyboard != Configuration.KEYBOARD_NOKEYS && configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO;
    WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    if (manager != null) {
      Display display=manager.getDefaultDisplay();
      if (display != null) {
        display.getMetrics(displayMetrics);
        display.getSize(displaySize);
      }
    }
    if (configuration.screenWidthDp != Configuration.SCREEN_WIDTH_DP_UNDEFINED) {
      int newSize=(int)Math.ceil(configuration.screenWidthDp * density);
      if (Math.abs(displaySize.x - newSize) > 3) {
        displaySize.x=newSize;
      }
    }
    if (configuration.screenHeightDp != Configuration.SCREEN_HEIGHT_DP_UNDEFINED) {
      int newSize=(int)Math.ceil(configuration.screenHeightDp * density);
      if (Math.abs(displaySize.y - newSize) > 3) {
        displaySize.y=newSize;
      }
    }
    if (roundMessageSize == 0) {
      if (AndroidUtilities.isTablet()) {
        roundMessageSize=(int)(AndroidUtilities.getMinTabletSide() * 0.6f);
      }
 else {
        roundMessageSize=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.6f);
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("display size = " + displaySize.x + " " + displaySize.y + " " + displayMetrics.xdpi + "x" + displayMetrics.ydpi);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
