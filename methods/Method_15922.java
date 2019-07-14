static Pager getAndReset(){
  try {
    return get();
  }
  finally {
    reset();
  }
}
