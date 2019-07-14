public void addConfiguredArch(Architecture architecture) throws BuildException {
  String name=architecture.getName();
  if (name == null) {
    throw new BuildException("Name is required.");
  }
  architectures.add(name);
}
