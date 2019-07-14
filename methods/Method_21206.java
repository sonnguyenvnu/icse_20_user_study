public void takeProjects(final @NonNull List<Project> projects){
  clearSections();
  insertSection(SECTION_PROJECTS_HEADER,Collections.singletonList(Empty.get()));
  insertSection(SECTION_PROJECTS,projects);
  notifyDataSetChanged();
}
