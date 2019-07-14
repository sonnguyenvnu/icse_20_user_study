@Provides DataLoaderRegistry dataLoaderRegistry(BookServiceGrpc.BookServiceFutureStub bookService){
  BatchLoader<String,Book> bookBatchLoader=keys -> {
    ListenableFuture<List<Book>> listenableFuture=Futures.transform(bookService.listBooks(ListBooksRequest.newBuilder().addAllIds(keys).setPageSize(keys.size()).build()),resp -> resp.getBooksList(),MoreExecutors.directExecutor());
    return FutureConverter.toCompletableFuture(listenableFuture);
  }
;
  DataLoaderRegistry registry=new DataLoaderRegistry();
  registry.register("books",new DataLoader<>(bookBatchLoader));
  return registry;
}
