/** 
 * Saves all projects to the data store
 * @param allModified
 */
protected void saveProjects(boolean allModified){
  List<SaveRecord> records=new ArrayList<SaveRecord>();
  LocalDateTime startTimeOfSave=LocalDateTime.now();
synchronized (this) {
    for (    long id : _projectsMetadata.keySet()) {
      ProjectMetadata metadata=getProjectMetadata(id);
      Project project=_projects.get(id);
      if (project != null) {
        boolean hasUnsavedChanges=metadata.getModified().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() >= project.getLastSave().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (hasUnsavedChanges) {
          long msecsOverdue=startTimeOfSave.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - project.getLastSave().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
          records.add(new SaveRecord(project,msecsOverdue));
        }
 else         if (!project.getProcessManager().hasPending() && startTimeOfSave.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - project.getLastSave().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() > PROJECT_FLUSH_DELAY) {
          _projects.remove(id).dispose();
        }
      }
    }
  }
  if (records.size() > 0) {
    Collections.sort(records,new Comparator<SaveRecord>(){
      @Override public int compare(      SaveRecord o1,      SaveRecord o2){
        if (o1.overdue < o2.overdue) {
          return 1;
        }
 else         if (o1.overdue > o2.overdue) {
          return -1;
        }
 else {
          return 0;
        }
      }
    }
);
    logger.info(allModified ? "Saving all modified projects ..." : "Saving some modified projects ...");
    for (int i=0; i < records.size() && (allModified || (LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - startTimeOfSave.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() < QUICK_SAVE_MAX_TIME)); i++) {
      try {
        saveProject(records.get(i).project);
      }
 catch (      Exception e) {
        e.printStackTrace();
        disposeUnmodifiedProjects();
      }
    }
  }
}
