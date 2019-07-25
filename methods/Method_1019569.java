@EventListener void handle(BookInstanceAddedToCatalogue event){
  bookRepository.save(new AvailableBook(new BookId(event.getBookId()),event.getType(),ourLibraryBranch(),Version.zero()));
}
