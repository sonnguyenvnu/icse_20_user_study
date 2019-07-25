@NotNull @Override public Navigatable create(@NotNull Object o){
  return new NodeNavigatable(myProject,(SNodeReference)o);
}
