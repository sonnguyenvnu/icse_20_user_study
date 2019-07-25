void register(@NotNull Generator generator){
  assertCanChange();
  myAttachedGenerators.add(generator);
}
