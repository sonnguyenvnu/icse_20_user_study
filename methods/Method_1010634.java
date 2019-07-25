@NotNull public Project create(@NotNull Environment env){
  if (isApplicable()) {
    Project emptyProject=env.createEmptyProject();
    return construct(emptyProject);
  }
  throw new IllegalStateException("Strategy " + this + " is not applicable -- cannot create project");
}
