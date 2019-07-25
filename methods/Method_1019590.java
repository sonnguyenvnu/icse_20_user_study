private PatronDatabaseEntity handle(BookCheckedOut event){
  return removeHoldIfPresent(event.getPatronId(),event.getBookId(),event.getLibraryBranchId());
}
