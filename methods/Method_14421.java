static private void createProjectSynchronously(final ImportingJob job,final String format,final ObjectNode optionObj,final List<Exception> exceptions,final Format record,final Project project){
  ProjectMetadata pm=createProjectMetadata(optionObj);
  record.parser.parse(project,pm,job,job.getSelectedFileRecords(),format,-1,optionObj,exceptions);
  if (!job.canceled) {
    if (exceptions.size() == 0) {
      project.update();
      ProjectManager.singleton.registerProject(project,pm);
      job.setProjectID(project.id);
      job.setState("created-project");
    }
 else {
      job.setError(exceptions);
    }
    job.touch();
    job.updating=false;
  }
}
