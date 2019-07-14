@Override public void onIntermediateImageSet(long id,@Nullable ImageInfo imageInfo){
  mControllerListener.onIntermediateImageSet(toStringId(id),imageInfo);
}
