public void commit(long offset){
  if (this.committed < offset)   this.committed=offset;
}
