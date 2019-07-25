@Override public SModel resolve(@NotNull SModelReference reference){
  if (myRepository == null) {
    return super.resolve(reference);
  }
  SModel resolved=reference.resolve(myRepository);
  if (resolved == null) {
    return null;
  }
  return myModules.contains(resolved.getModule()) || myAccessoriesOfUsedLanguages.contains(resolved) ? resolved : null;
}
