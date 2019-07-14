@Override public int getSmallHeight(){
  return cover != null ? sizes.cover.get(1) : thumbnail != null ? sizes.thumbnail.get(1) : icon != null ? sizes.icon.get(1) : image != null ? sizes.image.get(1) : sizes.large.get(1);
}
