public void dispose(){
  save(true);
  for (  Project project : _projects.values()) {
    if (project != null) {
      project.dispose();
    }
  }
  _projects.clear();
  _projectsMetadata.clear();
}
