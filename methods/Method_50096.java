@Override protected void configureSchema(){
  addMutationList(serviceToFields(ShelfServiceGrpc.ShelfServiceFutureStub.class,ImmutableList.of("createShelf","mergeShelves")));
}
