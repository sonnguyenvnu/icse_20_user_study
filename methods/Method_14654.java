/** 
 * Gets all the project Metadata currently held in memory.
 * @return
 */
@JsonIgnore public Map<Long,ProjectMetadata> getAllProjectMetadata(){
  for (  Project project : _projects.values()) {
    mergeEmptyUserMetadata(project.getMetadata());
  }
  return _projectsMetadata;
}
