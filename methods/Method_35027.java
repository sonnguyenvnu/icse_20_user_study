@Nonnull @Override public Collection<String> list(@Nonnull String directory) throws FileNotFoundException {
  Collection<FSFile> foundFiles=findFiles(directory);
  Collection<String> names=new ArrayList<>(foundFiles.size());
  for (  FSFile foundFile : foundFiles) {
    names.add(foundFile.path());
  }
  return names;
}
