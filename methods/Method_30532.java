@Override public String getSmallUrl(){
  return cover != null ? cover : thumbnail != null ? thumbnail : icon != null ? icon : image != null ? image : large;
}
