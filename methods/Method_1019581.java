@Override @EventListener public void handle(BookCheckedOut event){
  try {
    createNewCheckout(event);
  }
 catch (  DuplicateKeyException ex) {
  }
}
