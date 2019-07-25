@Override public void apply(@NotNull Collection<TemplateMappingConfiguration> tmc){
  mySteps.add(new Transform(tmc));
}
