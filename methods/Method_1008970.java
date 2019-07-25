/** 
 * Marshal the content tree rooted at <tt>jaxbElement</tt> into an output stream
 * @param os XML will be added to this stream.
 * @param namespacePrefixMapper namespacePrefixMapper
 * @throws JAXBException If any unexpected problem occurs during the marshalling.
 */
@Override public void marshal(java.io.OutputStream os,Object namespacePrefixMapper) throws JAXBException {
  String xmlString=XmlUtils.marshaltoString(getJaxbElement(),false,true,jc);
  int pos=xmlString.indexOf(":sldMaster ");
  int closeTagPos=xmlString.indexOf(">",pos);
  if (xmlString.substring(pos,closeTagPos).contains(VML_DECL)) {
  }
 else {
    xmlString=xmlString.substring(0,pos + 11) + VML_DECL + " " + xmlString.substring(pos + 11);
  }
  try {
    IOUtils.write(xmlString,os,"UTF-8");
  }
 catch (  IOException e) {
    throw new JAXBException(e.getMessage(),e);
  }
}
