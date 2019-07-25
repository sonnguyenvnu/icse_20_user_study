@NotNull public ModelDependencies build(@NotNull TextGenResult textResult){
  ModelDependencies modelDependencies=new ModelDependencies();
  for (  TextUnit tu : textResult.getUnits()) {
    if (tu.getState() == TextUnit.Status.Empty) {
      continue;
    }
    if (tu instanceof RegularTextUnit) {
      RootDependencies.Source co=((RegularTextUnit)tu).findContextObject(RootDependencies.Source.class);
      if (co != null) {
        RootDependencies deps=co.getDependencies();
        if (deps != null) {
          modelDependencies.addDependencies(deps);
        }
      }
    }
  }
  return modelDependencies;
}
