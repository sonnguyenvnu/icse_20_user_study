@Override public synchronized void createBook(CreateBookRequest request,StreamObserver<Book> responseObserver){
  String id=bookIdCounter.getAndIncrement() + "";
  booksById.put(id,request.getBook().toBuilder().setId(id).build());
  responseObserver.onNext(booksById.get(id));
  responseObserver.onCompleted();
}
