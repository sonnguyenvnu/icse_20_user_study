/** 
 * @param reportID the report ID which was registered in the registry.
 * @param dataContext "live" data to be merged in the template
 * @param options optional, used to customize the output in the case if convertion must be done.
 * @return the merged content eventually converted in another format (PDF or HTML)
 */
public byte[] process(String reportId,List<DataContext> dataContext,Options options) throws XDocReportException {
  XDocReportRegistry registry=getXDocReportRegistry();
  IXDocReport report=registry.getReport(reportId);
  if (report == null) {
    throw new XDocReportException("Cannot find report with the id=" + reportId);
  }
  return process(report,dataContext,options);
}
