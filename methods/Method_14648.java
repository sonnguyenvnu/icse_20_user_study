/** 
 * Saves a project and its metadata to the data store
 * @param id
 */
public void ensureProjectSaved(long id){
synchronized (this) {
    ProjectMetadata metadata=this.getProjectMetadata(id);
    if (metadata != null) {
      try {
        saveMetadata(metadata,id);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
    Project project=getProject(id);
    if (project != null && metadata != null && metadata.getModified().isAfter(project.getLastSave())) {
      try {
        saveProject(project);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}
