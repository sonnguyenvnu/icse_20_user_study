static public void previewParse(ImportingJob job,String format,ObjectNode optionObj,List<Exception> exceptions){
  Format record=ImportingManager.formatToRecord.get(format);
  if (record == null || record.parser == null) {
    return;
  }
  job.prepareNewProject();
  record.parser.parse(job.project,job.metadata,job,job.getSelectedFileRecords(),format,100,optionObj,exceptions);
  job.project.update();
}
