@EventListener void handle(BookReturned bookReturned){
  bookRepository.findBy(new BookId(bookReturned.getBookId())).map(book -> handleBookReturned(book,bookReturned)).map(this::saveBook);
}
