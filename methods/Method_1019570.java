@EventListener void handle(BookPlacedOnHold bookPlacedOnHold){
  bookRepository.findBy(new BookId(bookPlacedOnHold.getBookId())).map(book -> handleBookPlacedOnHold(book,bookPlacedOnHold)).map(this::saveBook);
}
