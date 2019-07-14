/** 
 * Reconstruct the project metadata on a best efforts basis.  The name is gone, so build something descriptive from the column names.  Recover the creation and modification times based on whatever files are available.
 * @param projectDir the project directory
 * @param id the proejct id
 * @return
 */
static public ProjectMetadata recover(File projectDir,long id){
  ProjectMetadata pm=null;
  Project p=ProjectUtilities.load(projectDir,id);
  if (p != null) {
    List<String> columnNames=p.columnModel.getColumnNames();
    String tempName="<recovered project> - " + columnNames.size() + " cols X " + p.rows.size() + " rows - " + StringUtils.join(columnNames,'|');
    p.dispose();
    long ctime=System.currentTimeMillis();
    long mtime=0;
    File dataFile=new File(projectDir,"data.zip");
    ctime=mtime=dataFile.lastModified();
    File historyDir=new File(projectDir,"history");
    File[] files=historyDir.listFiles();
    if (files != null) {
      for (      File f : files) {
        long time=f.lastModified();
        ctime=Math.min(ctime,time);
        mtime=Math.max(mtime,time);
      }
    }
    pm=new ProjectMetadata(LocalDateTime.ofInstant(Instant.ofEpochMilli(ctime),ZoneId.systemDefault()),LocalDateTime.ofInstant(Instant.ofEpochMilli(mtime),ZoneId.systemDefault()),tempName);
    logger.error("Partially recovered missing metadata project in directory " + projectDir + " - " + tempName);
  }
  return pm;
}
