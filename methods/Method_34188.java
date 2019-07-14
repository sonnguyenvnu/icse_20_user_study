@Override protected void writeInternal(OAuth2AccessToken accessToken,HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
  throw new UnsupportedOperationException("This converter is only used for converting from externally aqcuired form data");
}
