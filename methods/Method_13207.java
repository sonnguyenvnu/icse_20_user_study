@Override protected Collection<Container.Entry> getChildren(Container.Entry entry){
  return JarContainerEntryUtil.removeInnerTypeEntries(entry.getChildren());
}
