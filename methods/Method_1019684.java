/** 
 * @param document the template in a binary form
 * @param fieldsMetadatas fields metadata used to generate for instance lzy loop for ODT, Docx..row table.
 * @param templateEngineID the template engine ID....
 * @param dataContext "live" data to be merged in the template
 * @param options optional, used to customize the output in the case if convertion must be done.
 * @return the merged content eventually converted in another format (PDF or HTML)
 */
public byte[] process(byte[] document,FieldsMetadata fieldsMetadata,String templateEngineId,List<DataContext> dataContext,Options options) throws XDocReportException {
  IXDocReport report=loadReport(getXDocReportRegistry(),"",document,fieldsMetadata,templateEngineId,false,isCacheOriginalDocument());
  return process(report,dataContext,options);
}
