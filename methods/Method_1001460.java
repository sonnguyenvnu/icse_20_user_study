public boolean touches(Snake other){
  if (other.mergeable != MergeStrategy.FULL) {
    return false;
  }
  if (other.worm.isPureHorizontal()) {
    return false;
  }
  return same(this.getLast(),other.getFirst());
}
