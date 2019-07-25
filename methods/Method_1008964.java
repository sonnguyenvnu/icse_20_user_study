/** 
 * Marshal the content tree rooted at <tt>jaxbElement</tt> into a DOM tree.
 * @param node DOM nodes will be added as children of this node. This parameter must be a Node that accepts children ( {@link org.w3c.dom.Document}, {@link org.w3c.dom.DocumentFragment}, or {@link org.w3c.dom.Element})
 * @throws JAXBException If any unexpected problem occurs during the marshalling.
 */
public void marshal(org.w3c.dom.Node node,Object namespacePrefixMapper) throws JAXBException {
  try {
    Marshaller marshaller=jc.createMarshaller();
    NamespacePrefixMapperUtils.setProperty(marshaller,namespacePrefixMapper);
    getContents();
    setMceIgnorable((McIgnorableNamespaceDeclarator)namespacePrefixMapper);
    marshaller.marshal(jaxbElement,node);
    ((McIgnorableNamespaceDeclarator)namespacePrefixMapper).setMcIgnorable(null);
  }
 catch (  Docx4JException e) {
    throw new JAXBException(e);
  }
}
