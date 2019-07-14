public static SingleServedStubResult fromOptional(Optional<ServeEvent> servedStub){
  return new SingleServedStubResult(servedStub.orNull());
}
