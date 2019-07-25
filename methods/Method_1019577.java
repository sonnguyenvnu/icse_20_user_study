public CheckedOutBook handle(BookCheckedOut bookCheckedOut){
  return new CheckedOutBook(bookInformation,new LibraryBranchId(bookCheckedOut.getLibraryBranchId()),new PatronId(bookCheckedOut.getPatronId()),version);
}
