/** 
 * Merge template with Java model.
 * @param context
 * @param writer
 * @param template
 * @throws IOException
 * @throws XDocReportException
 */
private void process(IContext context,Writer writer,Template template) throws IOException, XDocReportException {
  try {
    Environment environment=template.createProcessingEnvironment(context,writer);
    environment.process();
  }
 catch (  TemplateException e) {
    throw new XDocReportException(e);
  }
}
