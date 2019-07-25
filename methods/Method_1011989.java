@Override public void names(@NotNull Iterable<SLanguage> elements,@NotNull BiConsumer<SLanguage,String> nameConsumer){
  elements.forEach(lang -> nameConsumer.accept(lang,lang.getQualifiedName()));
}
