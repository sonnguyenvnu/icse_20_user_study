private static String getReports(){
  StringBuilder buf=new StringBuilder();
  for (  String reportName : RendererFactory.REPORT_FORMAT_TO_RENDERER.keySet()) {
    Renderer renderer=RendererFactory.createRenderer(reportName,new Properties());
    buf.append("   ").append(reportName).append(": ");
    if (!reportName.equals(renderer.getName())) {
      buf.append(" Deprecated alias for '" + renderer.getName()).append(PMD.EOL);
      continue;
    }
    buf.append(renderer.getDescription()).append(PMD.EOL);
    for (    PropertyDescriptor<?> property : renderer.getPropertyDescriptors()) {
      buf.append("        ").append(property.name()).append(" - ");
      buf.append(property.description());
      Object deflt=property.defaultValue();
      if (deflt != null) {
        buf.append("   default: ").append(deflt);
      }
      buf.append(PMD.EOL);
    }
  }
  return buf.toString();
}
