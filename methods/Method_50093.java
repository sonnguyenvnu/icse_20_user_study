@Mutation("createBookAndAddToShelf") Book createBookAndAddToShelf(CreateBookRequest request,@Arg("shelf") GetShelfRequest shelfRequest,ShelfServiceGrpc.ShelfServiceBlockingStub shelfClient,BookServiceGrpc.BookServiceBlockingStub bookClient){
  Book book=bookClient.createBook(request);
  Shelf shelf=shelfClient.getShelf(shelfRequest);
  return book;
}
