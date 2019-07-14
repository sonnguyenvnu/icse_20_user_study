private void onPhotoShow(int index,final PlaceProviderObject object){
  currentIndex=-1;
  currentFileNames[0]=null;
  currentFileNames[1]=null;
  currentFileNames[2]=null;
  if (currentThumb != null) {
    currentThumb.release();
  }
  currentThumb=object != null ? object.thumb : null;
  menuItem.setVisibility(View.VISIBLE);
  menuItem.hideSubItem(gallery_menu_openin);
  actionBar.setTranslationY(0);
  captionTextView.setTag(null);
  captionTextView.setVisibility(View.GONE);
  for (int a=0; a < 3; a++) {
    if (radialProgressViews[a] != null) {
      radialProgressViews[a].setBackgroundState(-1,false);
    }
  }
  setImageIndex(index,true);
  if (currentMedia != null && isMediaVideo(currentIndex)) {
    onActionClick(false);
  }
}
