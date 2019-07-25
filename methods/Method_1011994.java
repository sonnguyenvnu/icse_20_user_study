@Override public void names(@NotNull Iterable<SModuleReference> elements,@NotNull BiConsumer<SModuleReference,String> nameConsumer){
  elements.forEach(mr -> nameConsumer.accept(mr,mr.getModuleName()));
}
