@Override public List<ServeEvent> select(){
  FluentIterable<ServeEvent> chain=FluentIterable.from(source);
  return chain.filter(new Predicate<ServeEvent>(){
    @Override public boolean apply(    ServeEvent input){
      return since == null || input.getRequest().getLoggedDate().after(since);
    }
  }
).limit(firstNonNull(limit,source.size())).toList();
}
