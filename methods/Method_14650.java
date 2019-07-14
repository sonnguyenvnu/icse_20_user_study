/** 
 * Flush all unmodified projects from memory.
 */
protected void disposeUnmodifiedProjects(){
synchronized (this) {
    for (    long id : _projectsMetadata.keySet()) {
      ProjectMetadata metadata=getProjectMetadata(id);
      Project project=_projects.get(id);
      if (project != null && !project.getProcessManager().hasPending() && metadata.getModified().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() < project.getLastSave().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) {
        _projects.remove(id).dispose();
      }
    }
  }
}
