/** 
 * finisherParams is a map of parameter values you can pass in, which named templates can be sensitive to (eg to set a color).
 * @param part
 * @param xpathsMap
 * @param finisherParams
 * @throws Docx4JException
 */
public void apply(JaxbXmlPart part,Map<String,org.opendope.xpaths.Xpaths.Xpath> xpathsMap,String filename,Map<String,Map<String,Object>> finisherParams) throws Docx4JException {
  if (xsltProvider == null) {
    log.debug("No XsltProvider, skipping finisher.");
    return;
  }
  Templates template;
  try {
    template=xsltProvider.getFinisherXslt(filename);
    if (template == null) {
      log.debug("No XsltFinisher, skipping.");
    }
  }
 catch (  TransformerConfigurationException e1) {
    throw new Docx4JException(e1.getMessage(),e1);
  }
  org.w3c.dom.Document doc=XmlUtils.marshaltoW3CDomDocument(part.getJaxbElement());
  try {
    DOMResult result=new DOMResult();
    Map<String,Object> transformParameters=new HashMap<String,Object>();
    transformParameters.put("customXmlDataStorageParts",part.getPackage().getCustomXmlDataStorageParts());
    transformParameters.put("wmlPackage",wordMLPackage);
    transformParameters.put("sourcePart",part);
    transformParameters.put("xPathsMap",xpathsMap);
    transformParameters.put("finisherParams",finisherParams);
    org.docx4j.XmlUtils.transform(doc,template,transformParameters,result);
{
      try {
        part.setJaxbElement(unmarshal(((org.w3c.dom.Document)result.getNode()),Docx4jProperties.getProperty("docx4j.model.datastorage.BindingTraverserXSLT.ValidationEventContinue",false)));
      }
 catch (      UnmarshalException e) {
        log.error("Problem: " + XmlUtils.w3CDomNodeToString(result.getNode()));
        throw e;
      }
    }
  }
 catch (  UnmarshalException e) {
    if (!Docx4jProperties.getProperty("docx4j.model.datastorage.BindingTraverserXSLT.ValidationEventContinue",false)) {
      log.error("Configured to fail in the case of content loss; " + "you can set property docx4j.model.datastorage.BindingTraverserXSLT.ValidationEventContinue if you wish to force output to be generated");
    }
    throw new Docx4JException("Problems applying bindings",e);
  }
catch (  Exception e) {
    throw new Docx4JException("Problems applying bindings",e);
  }
}
