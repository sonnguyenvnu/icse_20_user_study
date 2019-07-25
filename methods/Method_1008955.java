@Deprecated public void filter(FilterSettings filterSettings) throws Exception {
  if (filterTemplate == null) {
    Source xsltSource=new StreamSource(org.docx4j.utils.ResourceUtils.getResource("org/docx4j/openpackaging/packages/filter.xslt"));
    filterTemplate=XmlUtils.getTransformerTemplate(xsltSource);
  }
  transform(filterTemplate,filterSettings.getSettings());
}
