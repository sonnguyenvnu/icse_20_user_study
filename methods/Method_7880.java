public boolean isShowingImage(TLRPC.PageBlock object){
  return isPhotoVisible && !disableShowCheck && object != null && currentMedia == object;
}
