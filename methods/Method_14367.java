/** 
 * processFieldAsRecord parses Tree data for a single element and it's sub-elements, adding the parsed data as a row to the project
 * @param project
 * @param parser
 * @param rootColumnGroup
 * @throws ServletException
 */
static protected void processFieldAsRecord(Project project,TreeReader parser,ImportColumnGroup rootColumnGroup,ImportParameters parameter) throws TreeReaderException {
  if (logger.isTraceEnabled()) {
    logger.trace("processFieldAsRecord(Project,TreeReader,ImportColumnGroup)");
  }
  Serializable value=parser.getValue();
  ImportRecord record=null;
  if (value instanceof String) {
    String text=(String)value;
    if (parameter.trimStrings) {
      text=text.trim();
    }
    if (text.length() > 0 | !parameter.storeEmptyStrings) {
      record=new ImportRecord();
      addCell(project,rootColumnGroup,record,parser.getFieldName(),(String)value,parameter.storeEmptyStrings,parameter.guessDataType);
    }
  }
 else {
    record=new ImportRecord();
    addCell(project,rootColumnGroup,record,parser.getFieldName(),value);
  }
  if (record != null) {
    addImportRecordToProject(record,project,parameter.includeFileSources,parameter.fileSource);
  }
}
