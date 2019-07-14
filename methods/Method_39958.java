@Override public void configure(final HasSubstring annotation){
  this.substring=annotation.value();
  this.ignoreCase=annotation.ignoreCase();
}
