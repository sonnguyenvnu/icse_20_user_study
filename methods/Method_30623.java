@Subscribe(threadMode=ThreadMode.POSTING) public void onPhotoDeleted(PhotoDeletedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<Photo> photoList=get();
  for (int i=0, size=photoList.size(); i < size; ) {
    Photo photo=photoList.get(i);
    if (photo.id == event.photoId) {
      photoList.remove(i);
      getListener().onPhotoRemoved(getRequestCode(),i);
      --size;
    }
 else {
      ++i;
    }
  }
}
