private Binding findBinding(Binding b){
  List<Binding> existing=allBindings.get(b.getQname());
  if (existing != null) {
    for (    Binding eb : existing) {
      if (eb.equals(b)) {
        return eb;
      }
    }
  }
  return null;
}
