@Override public int getMediumWidth(){
  return image != null ? sizes.image.get(0) : large != null ? sizes.large.get(0) : cover != null ? sizes.cover.get(0) : thumbnail != null ? sizes.thumbnail.get(0) : sizes.icon.get(0);
}
