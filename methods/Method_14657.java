/** 
 * Removes project from memory
 * @param projectID
 */
protected void removeProject(long projectID){
  if (_projects.containsKey(projectID)) {
    _projects.remove(projectID).dispose();
  }
  if (_projectsMetadata.containsKey(projectID)) {
    _projectsMetadata.remove(projectID);
  }
}
