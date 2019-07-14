/** 
 * processRecord parses Tree data for a single element and it's sub-elements, adding the parsed data as a row to the project
 * @param project
 * @param parser
 * @param rootColumnGroup
 * @throws ServletException
 */
static protected void processRecord(Project project,TreeReader parser,ImportColumnGroup rootColumnGroup,ImportParameters parameter) throws TreeReaderException {
  if (logger.isTraceEnabled()) {
    logger.trace("processRecord(Project,TreeReader,ImportColumnGroup)");
  }
  ImportRecord record=new ImportRecord();
  processSubRecord(project,parser,rootColumnGroup,record,0,parameter);
  addImportRecordToProject(record,project,parameter.includeFileSources,parameter.fileSource);
}
