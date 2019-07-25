@Override public void load(List<LibraryContributor> contributors){
  for (  LibraryContributor contributor : contributors) {
    addContributor(contributor);
  }
  update(false);
}
