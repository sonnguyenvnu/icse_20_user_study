private PatronDatabaseEntity handle(BookPlacedOnHold event){
  booksOnHold.add(new HoldDatabaseEntity(event.getBookId(),event.getPatronId(),event.getLibraryBranchId(),event.getHoldTill()));
  return this;
}
