public ClassSymbol resolve(Inliner inliner) throws CouldNotResolveImportException {
  return inliner.resolveClass(getName());
}
