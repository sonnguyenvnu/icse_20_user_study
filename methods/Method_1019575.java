public BookOnHold handle(BookPlacedOnHold bookPlacedOnHold){
  return new BookOnHold(bookInformation,new LibraryBranchId(bookPlacedOnHold.getLibraryBranchId()),new PatronId(bookPlacedOnHold.getPatronId()),bookPlacedOnHold.getHoldTill(),version);
}
