public boolean isLimited(){
  return this.limit() != null && !this.isAllGone();
}
