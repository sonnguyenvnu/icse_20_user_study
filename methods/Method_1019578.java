@Override @Transactional @EventListener public void handle(BookPlacedOnHold event){
  try {
    createNewHold(event);
  }
 catch (  DuplicateKeyException ex) {
  }
}
