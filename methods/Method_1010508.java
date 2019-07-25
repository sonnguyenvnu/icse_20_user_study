@Override public void unload(List<LibraryContributor> contributors){
  for (  LibraryContributor contributor : contributors) {
    removeContributor(contributor);
  }
  update(false);
}
