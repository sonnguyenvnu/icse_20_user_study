@Override public void apply(@NotNull Collection<TemplateMappingConfiguration> tmc){
  mySteps.add(new PreparedEntry(new ArrayList<>(tmc)));
}
