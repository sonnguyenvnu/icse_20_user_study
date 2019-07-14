public void removeBinding(Binding b){
  for (  List<Binding> bs : allBindings.values()) {
    bs.remove(b);
  }
}
