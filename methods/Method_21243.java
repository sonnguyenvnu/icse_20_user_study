public void loadSearchProjects(final @NonNull List<Project> newProjects){
  clearSections();
  if (newProjects.size() > 0) {
    this.insertSection(SECTION_POPULAR_TITLE,Collections.emptyList());
    this.insertSection(SECTION_FEATURED_PROJECT,Collections.singletonList(Pair.create(newProjects.get(0),true)));
    this.insertSection(SECTION_PROJECT,Observable.from(newProjects.subList(1,newProjects.size())).map(p -> Pair.create(p,false)).toList().toBlocking().first());
  }
  notifyDataSetChanged();
}
