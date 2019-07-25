/** 
 * Replace the contents of this part with the output of passing it through your StAXHandler.  This is most efficient in the case where there has been no need for JAXB to unmarshal the contents.  In this case, it is possible to process then save the contents without incurring JAXB overhead (you may see 1/4 heap usage).
 * @param handler
 * @throws XMLStreamException
 * @throws Docx4JException
 * @throws JAXBException
 */
public void pipe(StAXHandlerInterface handler) throws XMLStreamException, Docx4JException, JAXBException {
  pipe(handler,null);
}
