@Override public int getLargeHeight(){
  return large != null ? sizes.large.get(1) : image != null ? sizes.image.get(1) : cover != null ? sizes.cover.get(1) : thumbnail != null ? sizes.thumbnail.get(1) : sizes.icon.get(1);
}
