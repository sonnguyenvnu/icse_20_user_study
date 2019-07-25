private void seek(long page){
  store.seek(page * pageSize);
}
