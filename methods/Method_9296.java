private void checkFreeDiscSpace(){
  if (Build.VERSION.SDK_INT >= 26) {
    return;
  }
  Utilities.globalQueue.postRunnable(() -> {
    if (!UserConfig.getInstance(currentAccount).isClientActivated()) {
      return;
    }
    try {
      SharedPreferences preferences=MessagesController.getGlobalMainSettings();
      if (Math.abs(preferences.getLong("last_space_check",0) - System.currentTimeMillis()) >= 3 * 24 * 3600 * 1000) {
        File path=FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE);
        if (path == null) {
          return;
        }
        long freeSpace;
        StatFs statFs=new StatFs(path.getAbsolutePath());
        if (Build.VERSION.SDK_INT < 18) {
          freeSpace=Math.abs(statFs.getAvailableBlocks() * statFs.getBlockSize());
        }
 else {
          freeSpace=statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        if (freeSpace < 1024 * 1024 * 100) {
          preferences.edit().putLong("last_space_check",System.currentTimeMillis()).commit();
          AndroidUtilities.runOnUIThread(() -> {
            try {
              AlertsCreator.createFreeSpaceDialog(LaunchActivity.this).show();
            }
 catch (            Throwable ignore) {
            }
          }
);
        }
      }
    }
 catch (    Throwable ignore) {
    }
  }
,2000);
}
