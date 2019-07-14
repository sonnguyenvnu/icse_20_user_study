@Override public void deleteProject(long projectID){
synchronized (this) {
    removeProject(projectID);
    File dir=getProjectDir(projectID);
    if (dir.exists()) {
      deleteDir(dir);
    }
  }
  saveWorkspace();
}
