/** 
 * Replace the contents of this part with the output of passing it through your SAXHandler.  This is offered as an alternative to the similar methods which use StAX.  If you are unsure which to use, you should probably use the StAX approach. This is most efficient in the case where there has been no need for JAXB to unmarshal the contents.  In this case, it is possible to process then save the contents without incurring JAXB overhead (you may see 1/4 heap usage).
 * @param saxHandler
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws Docx4JException
 * @throws IOException
 * @throws JAXBException
 */
public void pipe(SAXHandler saxHandler) throws ParserConfigurationException, SAXException, Docx4JException, IOException, JAXBException {
  SAXParserFactory spf=SAXParserFactory.newInstance();
  spf.setNamespaceAware(true);
  SAXParser saxParser=spf.newSAXParser();
  XMLReader xmlReader=saxParser.getXMLReader();
  xmlReader.setContentHandler(saxHandler);
  PartStore partStore=null;
  if (jaxbElement == null) {
    partStore=this.getPackage().getSourcePartStore();
    String name=this.getPartName().getName();
    InputStream is=partStore.loadPart(name.substring(1));
    if (is == null) {
      log.warn(name + " missing from part store");
      throw new Docx4JException(name + " missing from part store");
    }
 else {
      log.info("Fetching from part store " + name);
      xmlReader.parse(new InputSource(is));
    }
  }
 else {
    xmlReader.parse(new InputSource(XmlUtils.marshaltoInputStream(jaxbElement,true,this.jc)));
  }
  if (jaxbElement == null && partStore instanceof ZipPartStore) {
    log.debug("Just update the entry in the ZipPartStore");
    ByteArray byteArray=((ZipPartStore)partStore).getByteArray(this.getPartName().getName().substring(1));
    byteArray.setBytes(saxHandler.getOutputStream().toByteArray());
  }
 else {
    if (jaxbElement == null && log.isInfoEnabled()) {
      log.info(partStore.getClass().getName() + ": can't update in place, so unmarshalling.");
    }
 else {
      log.debug("unmarshalling");
    }
    jaxbElement=this.unmarshal(new ByteArrayInputStream(saxHandler.getOutputStream().toByteArray()));
  }
}
