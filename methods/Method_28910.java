public void clear(){
  if (inTransaction) {
    discard();
  }
}
