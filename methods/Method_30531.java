@Override public int getMediumHeight(){
  return image != null ? sizes.image.get(1) : large != null ? sizes.large.get(1) : cover != null ? sizes.cover.get(1) : thumbnail != null ? sizes.thumbnail.get(1) : sizes.icon.get(1);
}
