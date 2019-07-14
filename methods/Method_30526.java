@Override public String getLargeUrl(){
  return large != null ? large : image != null ? image : cover != null ? cover : thumbnail != null ? thumbnail : icon;
}
