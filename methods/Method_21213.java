public void takeProjects(final @NonNull List<Pair<Project,DiscoveryParams>> projects){
  setSection(SECTION_PROJECT_CARD_VIEW,projects);
  notifyDataSetChanged();
}
