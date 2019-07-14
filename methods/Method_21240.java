public void takeProject(final @NonNull Project project){
  sections().clear();
  addSection(Collections.singletonList(project));
  addSection(project.friends());
  notifyDataSetChanged();
}
