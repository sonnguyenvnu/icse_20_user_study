/** 
 * Create a Renderer instance based upon the configured reporting options. If withReportWriter then we'll configure it with a writer for the reportFile specified.
 * @param withReportWriter whether to configure a writer or not
 * @return A Renderer instance.
 */
public Renderer createRenderer(boolean withReportWriter){
  Renderer renderer=RendererFactory.createRenderer(reportFormat,reportProperties);
  renderer.setShowSuppressedViolations(showSuppressedViolations);
  if (withReportWriter) {
    renderer.setWriter(IOUtil.createWriter(reportFile));
  }
  return renderer;
}
