@Override public int getSmallWidth(){
  return cover != null ? sizes.cover.get(0) : thumbnail != null ? sizes.thumbnail.get(0) : icon != null ? sizes.icon.get(0) : image != null ? sizes.image.get(0) : sizes.large.get(0);
}
