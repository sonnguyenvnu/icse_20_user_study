private PatronDatabaseEntity handle(OverdueCheckoutRegistered event){
  checkouts.add(new OverdueCheckoutDatabaseEntity(event.getBookId(),event.getPatronId(),event.getLibraryBranchId()));
  return this;
}
