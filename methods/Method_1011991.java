@Override public void names(@NotNull Iterable<SModelReference> elements,@NotNull BiConsumer<SModelReference,String> nameConsumer){
  elements.forEach(mr -> nameConsumer.accept(mr,mr.getName().getValue()));
}
