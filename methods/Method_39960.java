@Override public void configure(final Length annotation){
  this.min=annotation.min();
  this.max=annotation.max();
}
