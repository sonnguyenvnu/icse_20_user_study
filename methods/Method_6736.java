public boolean hasValidGroupId(){
  return getGroupId() != 0 && photoThumbs != null && !photoThumbs.isEmpty();
}
