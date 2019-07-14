static void setupStorageSymlinks(final Context context){
  final String LOG_TAG="termux-storage";
  new Thread(){
    public void run(){
      try {
        File storageDir=new File(TermuxService.HOME_PATH,"storage");
        if (storageDir.exists()) {
          try {
            deleteFolder(storageDir);
          }
 catch (          IOException e) {
            Log.e(LOG_TAG,"Could not delete old $HOME/storage, " + e.getMessage());
            return;
          }
        }
        if (!storageDir.mkdirs()) {
          Log.e(LOG_TAG,"Unable to mkdirs() for $HOME/storage");
          return;
        }
        File sharedDir=Environment.getExternalStorageDirectory();
        Os.symlink(sharedDir.getAbsolutePath(),new File(storageDir,"shared").getAbsolutePath());
        File downloadsDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Os.symlink(downloadsDir.getAbsolutePath(),new File(storageDir,"downloads").getAbsolutePath());
        File dcimDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        Os.symlink(dcimDir.getAbsolutePath(),new File(storageDir,"dcim").getAbsolutePath());
        File picturesDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Os.symlink(picturesDir.getAbsolutePath(),new File(storageDir,"pictures").getAbsolutePath());
        File musicDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Os.symlink(musicDir.getAbsolutePath(),new File(storageDir,"music").getAbsolutePath());
        File moviesDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        Os.symlink(moviesDir.getAbsolutePath(),new File(storageDir,"movies").getAbsolutePath());
        final File[] dirs=context.getExternalFilesDirs(null);
        if (dirs != null && dirs.length > 1) {
          for (int i=1; i < dirs.length; i++) {
            File dir=dirs[i];
            if (dir == null)             continue;
            String symlinkName="external-" + i;
            Os.symlink(dir.getAbsolutePath(),new File(storageDir,symlinkName).getAbsolutePath());
          }
        }
      }
 catch (      Exception e) {
        Log.e(LOG_TAG,"Error setting up link",e);
      }
    }
  }
.start();
}
