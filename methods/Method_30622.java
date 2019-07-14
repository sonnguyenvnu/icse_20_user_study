protected void appendAndNotifyListener(List<Photo> photoList){
  append(photoList);
  getListener().onPhotoListAppended(getRequestCode(),photoList);
}
