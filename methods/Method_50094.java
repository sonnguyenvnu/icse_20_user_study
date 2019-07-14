@SchemaModification(addField="books",onType=Shelf.class) ListenableFuture<List<Book>> shelfToBooks(Shelf shelf,DataFetchingEnvironment environment){
  return FutureConverter.toListenableFuture(environment.<DataLoaderRegistry>getContext().<String,Book>getDataLoader("books").loadMany(shelf.getBookIdsList()));
}
