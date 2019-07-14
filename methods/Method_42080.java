public static void createShortcuts(Context context,List<Album> albums){
  for (  Album selectedAlbum : albums) {
    Intent shortcutIntent;
    shortcutIntent=new Intent(context,SplashScreen.class);
    shortcutIntent.setAction(SplashScreen.ACTION_OPEN_ALBUM);
    shortcutIntent.putExtra("albumPath",selectedAlbum.getPath());
    shortcutIntent.putExtra("albumId",selectedAlbum.getId());
    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    Intent addIntent=new Intent();
    addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,shortcutIntent);
    addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,selectedAlbum.getName());
    Media coverAlbum=selectedAlbum.getCover();
    File image=new File(coverAlbum.getPath());
    Bitmap bitmap=coverAlbum.isVideo() ? ThumbnailUtils.createVideoThumbnail(coverAlbum.getPath(),MediaStore.Images.Thumbnails.MINI_KIND) : BitmapFactory.decodeFile(image.getAbsolutePath(),new BitmapFactory.Options());
    if (bitmap == null) {
      Toast.makeText(context,R.string.error_thumbnail,Toast.LENGTH_SHORT).show();
      return;
    }
    bitmap=Bitmap.createScaledBitmap(getCroppedBitmap(bitmap),128,128,false);
    addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON,addWhiteBorder(bitmap,5));
    addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    context.sendBroadcast(addIntent);
  }
}
