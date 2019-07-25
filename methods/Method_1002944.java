static XMLObject deserialize(byte[] bytes){
  requireNonNull(bytes,"bytes");
  final ParserPool parserPool=XMLObjectProviderRegistrySupport.getParserPool();
  assert parserPool != null;
  final InputStream is=new ByteArrayInputStream(bytes);
  try {
    return XMLObjectSupport.unmarshallFromInputStream(parserPool,is);
  }
 catch (  XMLParserException|UnmarshallingException e) {
    throw new InvalidSamlRequestException("failed to deserialize an XML document bytes into a SAML object",e);
  }
}
