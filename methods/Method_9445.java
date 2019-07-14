private void openPhotoPicker(MediaController.AlbumEntry albumEntry,int type){
  ArrayList<MediaController.SearchImage> recentImages=null;
  if (albumEntry == null) {
    if (type == 0) {
      recentImages=recentWebImages;
    }
 else     if (type == 1) {
      recentImages=recentGifImages;
    }
  }
  PhotoPickerActivity fragment;
  if (albumEntry != null) {
    fragment=new PhotoPickerActivity(type,albumEntry,selectedPhotos,selectedPhotosOrder,recentImages,selectPhotoType,allowCaption,chatActivity);
    fragment.setDelegate(new PhotoPickerActivity.PhotoPickerActivityDelegate(){
      @Override public void selectedPhotosChanged(){
        if (pickerBottomLayout != null) {
          pickerBottomLayout.updateSelectedCount(selectedPhotos.size(),true);
        }
      }
      @Override public void actionButtonPressed(      boolean canceled){
        removeSelfFromStack();
        if (!canceled) {
          sendSelectedPhotos(selectedPhotos,selectedPhotosOrder);
        }
      }
    }
);
  }
 else {
    final HashMap<Object,Object> photos=new HashMap<>();
    final ArrayList<Object> order=new ArrayList<>();
    fragment=new PhotoPickerActivity(type,albumEntry,photos,order,recentImages,selectPhotoType,allowCaption,chatActivity);
    fragment.setDelegate(new PhotoPickerActivity.PhotoPickerActivityDelegate(){
      @Override public void selectedPhotosChanged(){
      }
      @Override public void actionButtonPressed(      boolean canceled){
        removeSelfFromStack();
        if (!canceled) {
          sendSelectedPhotos(photos,order);
        }
      }
    }
);
  }
  fragment.setMaxSelectedPhotos(maxSelectedPhotos);
  presentFragment(fragment);
}
