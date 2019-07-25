public AvailableBook handle(BookReturned bookReturned){
  return new AvailableBook(bookInformation,new LibraryBranchId(bookReturned.getLibraryBranchId()),version);
}
