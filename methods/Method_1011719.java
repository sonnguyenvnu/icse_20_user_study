/** 
 * Do not change this method's signature. It is used from MigrationWorker
 */
public static void migrate(final Project project) throws Exception {
  MigrationRegistry m=ProjectHelper.toIdeaProject(project).getComponent(MigrationRegistry.class);
  MigrationSession session=new AntTaskExecutionUtil.MyMigrationSession(project);
  ProgressMonitorAdapter progress=new ProgressMonitorAdapter(new EmptyProgressIndicator());
  final Properties properties=new Properties();
  properties.setProperty(ERR_CODE_KEY,"0");
  MigrationTask task=new MigrationTask(session,progress){
    @Override protected void error(    final MigrationError error){
      if (LOG.isEnabledFor(Level.ERROR)) {
        LOG.error(error.getMessage());
      }
      project.getRepository().getModelAccess().runReadAction(new Runnable(){
        public void run(){
          for (          IssueKindReportItem p : Sequence.fromIterable(error.getProblems(new EmptyProgressIndicator()))) {
            String problemMsg=p.getMessage() + " (reason object: " + IssueKindReportItem.PATH_OBJECT.get(p) + ")";
            if (LOG.isEnabledFor(Level.ERROR)) {
              LOG.error("- " + problemMsg);
            }
          }
        }
      }
);
      properties.setProperty(ERR_CODE_KEY,"1");
    }
  }
;
  task.run();
  project.getRepository().getModelAccess().runWriteInEDT(new Runnable(){
    public void run(){
      project.getRepository().saveAll();
    }
  }
);
  try {
    File file=new File(OUT_FILE_NAME);
    FileOutputStream fileOut=new FileOutputStream(file);
    properties.store(fileOut,"");
    fileOut.close();
  }
 catch (  IOException e) {
    if (LOG.isEnabledFor(Level.ERROR)) {
      LOG.error("Exception on saving result file " + OUT_FILE_NAME,e);
    }
  }
}
