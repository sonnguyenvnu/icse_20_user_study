@Override public Optional<ServeEvent> getServeEvent(final UUID id){
  return tryFind(serveEvents,new Predicate<ServeEvent>(){
    @Override public boolean apply(    ServeEvent input){
      return input.getId().equals(id);
    }
  }
);
}
