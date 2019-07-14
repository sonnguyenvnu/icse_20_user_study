private static Predicate<ServeEvent> withId(final UUID id){
  return new Predicate<ServeEvent>(){
    @Override public boolean apply(    ServeEvent input){
      return input.getId().equals(id);
    }
  }
;
}
