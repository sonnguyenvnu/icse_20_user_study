protected File getHistoryDir(HistoryEntry historyEntry){
  File dir=new File(((FileProjectManager)ProjectManager.singleton).getProjectDir(historyEntry.projectID),"history");
  dir.mkdirs();
  return dir;
}
