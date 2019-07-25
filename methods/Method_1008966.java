public E unmarshal(org.w3c.dom.Element el) throws JAXBException {
  try {
    Unmarshaller u=jc.createUnmarshaller();
    JaxbValidationEventHandler eventHandler=new JaxbValidationEventHandler();
    eventHandler.setContinue(false);
    u.setEventHandler(eventHandler);
    Unmarshaller.Listener docx4jUnmarshallerListener=new Docx4jUnmarshallerListener(this);
    u.setListener(docx4jUnmarshallerListener);
    try {
      jaxbElement=(E)XmlUtils.unwrap(u.unmarshal(el));
    }
 catch (    UnmarshalException ue) {
      log.info("encountered unexpected content; pre-processing");
      try {
        org.w3c.dom.Document doc;
        if (el instanceof org.w3c.dom.Document) {
          doc=(org.w3c.dom.Document)el;
        }
 else {
          doc=el.getOwnerDocument();
        }
        eventHandler.setContinue(true);
        JAXBResult result=XmlUtils.prepareJAXBResult(jc);
        Templates mcPreprocessorXslt=JaxbValidationEventHandler.getMcPreprocessor();
        XmlUtils.transform(doc,mcPreprocessorXslt,null,result);
        jaxbElement=(E)XmlUtils.unwrap(result.getResult());
      }
 catch (      Exception e) {
        throw new JAXBException("Preprocessing exception",e);
      }
    }
    return jaxbElement;
  }
 catch (  JAXBException e) {
    log.error(e.getMessage(),e);
    throw e;
  }
}
