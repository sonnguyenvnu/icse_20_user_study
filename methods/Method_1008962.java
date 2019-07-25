/** 
 * Replace the contents of this part with the output of passing it through your StAXHandler.  This is most efficient in the case where there has been no need for JAXB to unmarshal the contents.  In this case, it is possible to process then save the contents without incurring JAXB overhead (you may see 1/4 heap usage).
 * @param handler
 * @param filter
 * @throws XMLStreamException
 * @throws Docx4JException
 * @throws JAXBException
 */
public void pipe(StAXHandlerInterface handler,StreamFilter filter) throws XMLStreamException, Docx4JException, JAXBException {
  XMLInputFactory xmlif=null;
  xmlif=XMLInputFactory.newInstance();
  xmlif.setProperty(XMLInputFactory.IS_VALIDATING,Boolean.FALSE);
  xmlif.setProperty(XMLInputFactory.IS_COALESCING,Boolean.TRUE);
  xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE,Boolean.TRUE);
  xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,Boolean.TRUE);
  xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,Boolean.FALSE);
  xmlif.setXMLReporter((new XMLReporter(){
    @Override public void report(    String message,    String errorType,    Object relatedInformation,    Location location) throws XMLStreamException {
      log.warn("Error:" + errorType + ", " + message + " at line " + location.getLineNumber() + ", col " + location.getColumnNumber());
    }
  }
));
  XMLStreamReader xmlr=null;
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
      if (filter == null) {
        xmlr=xmlif.createXMLStreamReader(is);
      }
 else {
        xmlr=xmlif.createFilteredReader(xmlif.createXMLStreamReader(is),filter);
      }
    }
  }
 else {
    if (filter == null) {
      xmlr=xmlif.createXMLStreamReader(XmlUtils.marshaltoInputStream(jaxbElement,true,this.jc));
    }
 else {
      xmlr=xmlif.createFilteredReader(xmlif.createXMLStreamReader(XmlUtils.marshaltoInputStream(jaxbElement,true,this.jc)),filter);
    }
  }
  XMLOutputFactory outputFactory=XMLOutputFactory.newInstance();
  XMLStreamWriter xmlWriter=null;
  ByteArrayOutputStream baos=null;
  if (jaxbElement == null) {
    baos=new ByteArrayOutputStream();
    xmlWriter=outputFactory.createXMLStreamWriter(baos,"UTF-8");
  }
 else {
    baos=new ByteArrayOutputStream();
    xmlWriter=outputFactory.createXMLStreamWriter(baos,"UTF-8");
  }
  try {
    log.debug("StAX implementation details:");
    log.debug(xmlr.getClass().getName());
    log.debug(xmlWriter.getClass().getName());
    handler.handle(xmlr,xmlWriter);
  }
 catch (  LocationAwareXMLStreamException e) {
    log.error(e.getMessage() + " at line " + e.getLocation().getLineNumber() + ", col " + e.getLocation().getColumnNumber());
    e.getCause().printStackTrace();
    InputStream is=null;
    if (jaxbElement == null) {
      partStore=this.getPackage().getSourcePartStore();
      String name=this.getPartName().getName();
      is=partStore.loadPart(name.substring(1));
      if (is == null) {
        log.warn(name + " missing from part store");
        throw new Docx4JException(name + " missing from part store");
      }
    }
 else {
      is=XmlUtils.marshaltoInputStream(jaxbElement,true,this.jc);
    }
    if (is != null) {
      try {
        List<String> lines=IOUtils.readLines(is);
        String line=lines.get(e.getLocation().getLineNumber() - 1);
        int PRIOR_CHARS=100;
        int start=0;
        if (e.getLocation().getColumnNumber() > PRIOR_CHARS) {
          start=e.getLocation().getColumnNumber() - PRIOR_CHARS;
        }
        int end=e.getLocation().getColumnNumber() + PRIOR_CHARS;
        if (end > line.length() - 1) {
          end=line.length() - 1;
        }
        log.error("error is at pos " + PRIOR_CHARS + " in " + line.substring(start,end));
      }
 catch (      IOException e1) {
        e1.printStackTrace();
      }
    }
    throw (XMLStreamException)e.getCause();
  }
  xmlr.close();
  xmlWriter.flush();
  xmlWriter.close();
  if (jaxbElement == null && partStore instanceof ZipPartStore) {
    log.debug("Just update the entry in the ZipPartStore");
    ByteArray byteArray=((ZipPartStore)partStore).getByteArray(this.getPartName().getName().substring(1));
    byteArray.setBytes(baos.toByteArray());
  }
 else {
    if (jaxbElement == null && log.isInfoEnabled()) {
      log.info(partStore.getClass().getName() + ": can't update in place, so unmarshalling.");
    }
 else {
      log.debug("unmarshalling");
    }
    jaxbElement=this.unmarshal(new ByteArrayInputStream(baos.toByteArray()));
  }
}
