/** 
 * Generates an XML representation of the content model. <p> Optionally, items inside a container will be represented in the XML, the container elements then have nested item elements. Although this parser can read such a structure, it is unclear whether other DIDL parsers should and actually do support this XML. </p>
 * @param content     The content model.
 * @param nestedItems <code>true</code> if nested item elements should be rendered for containers.
 * @return An XML representation.
 * @throws Exception
 */
public String generate(DIDLContent content,boolean nestedItems) throws Exception {
  return documentToString(buildDOM(content,nestedItems),true);
}
