public void openGallery(){
  if (parentFragment != null) {
    if (Build.VERSION.SDK_INT >= 23 && parentFragment.getParentActivity() != null) {
      if (parentFragment.getParentActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        parentFragment.getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
        return;
      }
    }
    PhotoAlbumPickerActivity fragment=new PhotoAlbumPickerActivity(2,false,false,null);
    fragment.setAllowSearchImages(false);
    fragment.setDelegate(new PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate(){
      @Override public void didSelectPhotos(      ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
        WallpaperUpdater.this.didSelectPhotos(photos);
      }
      @Override public void startPhotoSelectActivity(){
        try {
          Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
          photoPickerIntent.setType("image/*");
          parentActivity.startActivityForResult(photoPickerIntent,11);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
);
    parentFragment.presentFragment(fragment);
  }
 else {
    Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
    photoPickerIntent.setType("image/*");
    parentActivity.startActivityForResult(photoPickerIntent,11);
  }
}
