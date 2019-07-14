@Override protected final void writeToResult(E accessToken,HttpHeaders headers,Result result) throws IOException {
  I convertedAccessToken=convertToInternal(accessToken);
  try {
    createMarshaller().marshal(convertedAccessToken,result);
  }
 catch (  MarshalException ex) {
    throw new HttpMessageNotWritableException("Could not marshal [" + accessToken + "]: " + ex.getMessage(),ex);
  }
catch (  JAXBException ex) {
    throw new HttpMessageConversionException("Could not instantiate JAXBContext: " + ex.getMessage(),ex);
  }
}
