@Override public String getMediumUrl(){
  return image != null ? image : large != null ? large : cover != null ? cover : thumbnail != null ? thumbnail : icon;
}
