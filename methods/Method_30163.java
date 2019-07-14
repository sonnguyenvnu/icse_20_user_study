private static BookFragmentResource newInstance(long bookId,SimpleBook simpleBook,Book book){
  return new BookFragmentResource().setArguments(bookId,simpleBook,book);
}
