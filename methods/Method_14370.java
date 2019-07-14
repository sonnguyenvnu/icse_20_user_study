final static private ObjectNode descendElement(XMLStreamReader parser,PreviewParsingState state){
  ObjectNode result=ParsingUtilities.mapper.createObjectNode();
{
    String name=parser.getLocalName();
    JSONUtilities.safePut(result,"n",name);
    String prefix=parser.getPrefix();
    if (prefix != null) {
      JSONUtilities.safePut(result,"p",prefix);
    }
    String nsUri=parser.getNamespaceURI();
    if (nsUri != null) {
      JSONUtilities.safePut(result,"uri",nsUri);
    }
  }
  int namespaceCount=parser.getNamespaceCount();
  if (namespaceCount > 0) {
    ArrayNode namespaces=result.putArray("ns");
    for (int i=0; i < namespaceCount; i++) {
      ObjectNode namespace=ParsingUtilities.mapper.createObjectNode();
      namespaces.add(namespace);
      JSONUtilities.safePut(namespace,"p",parser.getNamespacePrefix(i));
      JSONUtilities.safePut(namespace,"uri",parser.getNamespaceURI(i));
    }
  }
  int attributeCount=parser.getAttributeCount();
  if (attributeCount > 0) {
    ArrayNode attributes=result.putArray("a");
    for (int i=0; i < attributeCount; i++) {
      ObjectNode attribute=ParsingUtilities.mapper.createObjectNode();
      attributes.add(attribute);
      JSONUtilities.safePut(attribute,"n",parser.getAttributeLocalName(i));
      JSONUtilities.safePut(attribute,"v",parser.getAttributeValue(i));
      String prefix=parser.getAttributePrefix(i);
      if (prefix != null) {
        JSONUtilities.safePut(attribute,"p",prefix);
      }
    }
  }
  ArrayNode children=ParsingUtilities.mapper.createArrayNode();
  try {
    while (parser.hasNext() && state.tokenCount < PREVIEW_PARSING_LIMIT) {
      int tokenType=parser.next();
      state.tokenCount++;
      if (tokenType == XMLStreamConstants.END_ELEMENT) {
        break;
      }
 else       if (tokenType == XMLStreamConstants.START_ELEMENT) {
        ObjectNode childElement=descendElement(parser,state);
        if (childElement != null) {
          children.add(childElement);
        }
      }
 else       if (tokenType == XMLStreamConstants.CHARACTERS || tokenType == XMLStreamConstants.CDATA || tokenType == XMLStreamConstants.SPACE) {
        ObjectNode childElement=ParsingUtilities.mapper.createObjectNode();
        JSONUtilities.safePut(childElement,"t",parser.getText());
        children.add(childElement);
      }
 else {
      }
    }
  }
 catch (  XMLStreamException e) {
    logger.error("Error generating parser UI initialization data for XML file",e);
  }
  if (children.size() > 0) {
    result.put("c",children);
  }
  return result;
}
