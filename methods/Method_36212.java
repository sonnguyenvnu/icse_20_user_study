private Iterable<LoggedRequest> getRequests(){
  return transform(serveEvents,new Function<ServeEvent,LoggedRequest>(){
    public LoggedRequest apply(    ServeEvent input){
      return input.getRequest();
    }
  }
);
}
