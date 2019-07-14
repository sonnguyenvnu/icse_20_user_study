public BackupImageView getImageView(int a){
  if (a >= itemsCount) {
    return null;
  }
  return photoVideoViews[a].imageView;
}
