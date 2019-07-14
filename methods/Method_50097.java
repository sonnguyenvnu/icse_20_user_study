@Query("listShelves") ListenableFuture<ListShelvesResponse> listShelves(ListShelvesRequest request,ShelfServiceGrpc.ShelfServiceFutureStub client){
  return client.listShelves(request);
}
