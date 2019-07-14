private ImageSize chooseImageSize(){
  ViewGroup.LayoutParams layoutParams=mRecyclerView.getLayoutParams();
  if (layoutParams == null) {
    return ImageSize.LARGE_THUMBNAIL;
  }
  int size=calcDesiredSize(this,layoutParams.width,layoutParams.height);
  if (size <= 90) {
    return ImageSize.SMALL_SQUARE;
  }
 else   if (size <= 160) {
    return ImageSize.SMALL_THUMBNAIL;
  }
 else   if (size <= 320) {
    return ImageSize.MEDIUM_THUMBNAIL;
  }
 else   if (size <= 640) {
    return ImageSize.LARGE_THUMBNAIL;
  }
 else   if (size <= 1024) {
    return ImageSize.HUGE_THUMBNAIL;
  }
 else {
    return ImageSize.ORIGINAL_IMAGE;
  }
}
