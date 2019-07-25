@NotNull @Override public Project create(@NotNull Environment env){
  ProjectStrategy firstApplicable=getFirstApplicable();
  if (firstApplicable != null) {
    return firstApplicable.create(env);
  }
  throw new CompositeProjectStrategy.NoStrategyFoundException("Could not create project with given strategies, nothing is applicable");
}
