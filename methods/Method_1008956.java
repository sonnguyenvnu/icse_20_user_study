public Object unmarshal(String filename) throws JAXBException {
  java.io.InputStream is=null;
  try {
    is=org.docx4j.utils.ResourceUtils.getResource("org/docx4j/openpackaging/parts/DrawingML/" + filename);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return unmarshal(is);
}
