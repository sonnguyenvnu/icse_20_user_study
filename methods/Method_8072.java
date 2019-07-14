public void setPhotoEntry(MediaController.PhotoEntry entry,boolean needCheckShow,boolean last){
  pressed=false;
  photoEntry=entry;
  isLast=last;
  if (photoEntry.isVideo) {
    imageView.setOrientation(0,true);
    videoInfoContainer.setVisibility(VISIBLE);
    int minutes=photoEntry.duration / 60;
    int seconds=photoEntry.duration - minutes * 60;
    videoTextView.setText(String.format("%d:%02d",minutes,seconds));
  }
 else {
    videoInfoContainer.setVisibility(INVISIBLE);
  }
  if (photoEntry.thumbPath != null) {
    imageView.setImage(photoEntry.thumbPath,null,getResources().getDrawable(R.drawable.nophotos));
  }
 else   if (photoEntry.path != null) {
    if (photoEntry.isVideo) {
      imageView.setImage("vthumb://" + photoEntry.imageId + ":" + photoEntry.path,null,getResources().getDrawable(R.drawable.nophotos));
    }
 else {
      imageView.setOrientation(photoEntry.orientation,true);
      imageView.setImage("thumb://" + photoEntry.imageId + ":" + photoEntry.path,null,getResources().getDrawable(R.drawable.nophotos));
    }
  }
 else {
    imageView.setImageResource(R.drawable.nophotos);
  }
  boolean showing=needCheckShow && PhotoViewer.isShowingImage(photoEntry.path);
  imageView.getImageReceiver().setVisible(!showing,true);
  checkBox.setAlpha(showing ? 0.0f : 1.0f);
  videoInfoContainer.setAlpha(showing ? 0.0f : 1.0f);
  requestLayout();
}
