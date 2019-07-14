/** 
 * Process the xml file in the default location, and schedule all of the jobs defined within it. <p>Note that we will set overWriteExistingJobs after the default xml is parsed. 
 */
public void processFileAndScheduleJobs(Scheduler sched,boolean overWriteExistingJobs) throws Exception {
  String fileName=QUARTZ_XML_DEFAULT_FILE_NAME;
  processFile(fileName,getSystemIdForFileName(fileName));
  setOverWriteExistingData(overWriteExistingJobs);
  executePreProcessCommands(sched);
  scheduleJobs(sched);
}
