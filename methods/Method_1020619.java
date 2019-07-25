@Nonnull synchronized First<M,F> init(){
  First<M,F> first=init.init(currentModel);
  currentModel=first.model();
  return first;
}
