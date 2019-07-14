@Override protected final E readFromSource(Class<? extends E> clazz,HttpHeaders headers,Source source) throws IOException {
  try {
    JAXBElement<? extends I> jaxbElement=createUnmarshaller().unmarshal(source,internalClass);
    return convertToExternal(jaxbElement.getValue());
  }
 catch (  UnmarshalException ex) {
    throw new HttpMessageNotReadableException("Could not unmarshal to [" + clazz + "]: " + ex.getMessage(),ex);
  }
catch (  JAXBException ex) {
    throw new HttpMessageConversionException("Could not instantiate JAXBContext: " + ex.getMessage(),ex);
  }
}
