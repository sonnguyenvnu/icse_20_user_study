@Override @EventListener public void handle(BookReturned event){
  int results=markAsReturned(event);
  if (results == 0) {
    insertAsReturnedWithCheckedOutEventMissing(event);
  }
}
