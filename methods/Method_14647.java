/** 
 * Registers the project in the memory of the current session
 * @param project
 * @param projectMetadata
 */
public void registerProject(Project project,ProjectMetadata projectMetadata){
synchronized (this) {
    _projects.put(project.id,project);
    _projectsMetadata.put(project.id,projectMetadata);
    if (_projectsTags == null)     _projectsTags=new HashMap<String,Integer>();
    String[] tags=projectMetadata.getTags();
    if (tags != null) {
      for (      String tag : tags) {
        if (_projectsTags.containsKey(tag)) {
          _projectsTags.put(tag,_projectsTags.get(tag) + 1);
        }
 else {
          _projectsTags.put(tag,1);
        }
      }
    }
  }
}
