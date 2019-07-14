@NonNull public StreamReader setFilter(Predicate<String> filter){
  this.filter=filter;
  return this;
}
