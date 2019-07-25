private PatronDatabaseEntity handle(BookHoldCanceled event){
  return removeHoldIfPresent(event.getPatronId(),event.getBookId(),event.getLibraryBranchId());
}
