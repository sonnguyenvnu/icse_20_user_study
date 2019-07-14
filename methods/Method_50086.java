@Override public synchronized void deleteBook(DeleteBookRequest request,StreamObserver<Empty> responseObserver){
  if (booksById.remove(request.getId()) == null) {
    throw new RuntimeException(String.format("Book with id=%s not found",request.getId()));
  }
}
