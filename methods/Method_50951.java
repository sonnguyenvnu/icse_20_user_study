private void dumpDocToWriter(Document doc,Writer writer){
  try {
    TransformerFactory tf=TransformerFactory.newInstance();
    Transformer transformer=tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.METHOD,"xml");
    transformer.setOutputProperty(OutputKeys.ENCODING,encoding);
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS,"codefragment");
    transformer.transform(new DOMSource(doc),new StreamResult(writer));
  }
 catch (  TransformerException e) {
    throw new IllegalStateException(e);
  }
}
