public void convert(InputStream in,OutputStream out,Options options) throws XDocConverterException {
  try {
    WordprocessingMLPackage wordMLPackage=WordprocessingMLPackage.load(in);
    AbstractHtmlExporter exporter=new HtmlExporterNG2();
    javax.xml.transform.stream.StreamResult result=new javax.xml.transform.stream.StreamResult(out);
    exporter.html(wordMLPackage,result,toHtmlSettings(options));
  }
 catch (  Exception e) {
    LOGGER.severe(e.getMessage());
    throw new XDocConverterException(e);
  }
}
