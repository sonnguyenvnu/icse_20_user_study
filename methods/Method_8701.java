public void openGallery(){
  if (parentFragment == null) {
    return;
  }
  if (Build.VERSION.SDK_INT >= 23 && parentFragment != null && parentFragment.getParentActivity() != null) {
    if (parentFragment.getParentActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      parentFragment.getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
      return;
    }
  }
  PhotoAlbumPickerActivity fragment=new PhotoAlbumPickerActivity(1,false,false,null);
  fragment.setDelegate(new PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate(){
    @Override public void didSelectPhotos(    ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
      ImageUpdater.this.didSelectPhotos(photos);
    }
    @Override public void startPhotoSelectActivity(){
      try {
        Intent photoPickerIntent=new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        parentFragment.startActivityForResult(photoPickerIntent,14);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
);
  parentFragment.presentFragment(fragment);
}
