@Override public void save(Book book){
  findBy(book.bookId()).map(entity -> updateOptimistically(book)).onEmpty(() -> insertNew(book));
}
