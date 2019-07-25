@NotNull @Override public Navigatable create(@NotNull Object o){
  return new ModelNavigatable(myProject,(SModelReference)o);
}
