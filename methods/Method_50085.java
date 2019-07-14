@Override public synchronized void streamBooks(ListBooksRequest request,StreamObserver<ListBooksResponse> responseObserver){
  NavigableMap<String,Book> cursor=booksById;
  if (!request.getPageToken().isEmpty()) {
    String pageToken=decodePageToken(request.getPageToken());
    cursor=cursor.tailMap(pageToken,false);
  }
  cursor.values().stream().limit(request.getPageSize() > 0 ? request.getPageSize() : DEFAULT_PAGE_SIZE).forEach(book -> responseObserver.onNext(ListBooksResponse.newBuilder().addBooks(book).setNextPageToken(encodePageToken(book.getId())).build()));
  responseObserver.onCompleted();
}
