public void putRef(@NotNull Node node,@NotNull Collection<Binding> bs){
  if (!(node instanceof Url)) {
    List<Binding> bindings=references.get(node);
    if (bindings == null) {
      bindings=new ArrayList<>(1);
      references.put(node,bindings);
    }
    for (    Binding b : bs) {
      if (!bindings.contains(b)) {
        bindings.add(b);
      }
      b.addRef(node);
    }
  }
}
