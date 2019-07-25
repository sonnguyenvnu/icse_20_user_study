public Document fields(String... names){
  for (  String name : names) {
    fields().add(name);
  }
  return this;
}
