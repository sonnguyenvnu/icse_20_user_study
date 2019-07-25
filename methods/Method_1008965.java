/** 
 * Marshal the content tree rooted at <tt>jaxbElement</tt> into an output stream, using org.docx4j.jaxb.NamespacePrefixMapper.
 * @param os XML will be added to this stream.
 * @throws JAXBException If any unexpected problem occurs during the marshalling.
 */
public void marshal(java.io.OutputStream os) throws JAXBException {
  marshal(os,NamespacePrefixMapperUtils.getPrefixMapper());
}
