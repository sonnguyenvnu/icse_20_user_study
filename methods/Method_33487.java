public void setLoaded(boolean end){
  loaded=end;
  if (!end) {
    this.loadMoreComplete();
  }
}
