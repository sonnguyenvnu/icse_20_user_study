/** 
 * Deconstructs response into given content handler
 * @param entity returned HttpEntity
 * @return deconstructed response
 * @throws java.io.IOException if there is problem assembling SAX response from stream
 * @see cz.msebera.android.httpclient.HttpEntity
 */
@Override protected byte[] getResponseData(HttpEntity entity) throws IOException {
  if (entity != null) {
    InputStream instream=entity.getContent();
    InputStreamReader inputStreamReader=null;
    if (instream != null) {
      try {
        SAXParserFactory sfactory=SAXParserFactory.newInstance();
        SAXParser sparser=sfactory.newSAXParser();
        XMLReader rssReader=sparser.getXMLReader();
        rssReader.setContentHandler(handler);
        inputStreamReader=new InputStreamReader(instream,getCharset());
        rssReader.parse(new InputSource(inputStreamReader));
      }
 catch (      SAXException e) {
        AsyncHttpClient.log.e(LOG_TAG,"getResponseData exception",e);
      }
catch (      ParserConfigurationException e) {
        AsyncHttpClient.log.e(LOG_TAG,"getResponseData exception",e);
      }
 finally {
        AsyncHttpClient.silentCloseInputStream(instream);
        if (inputStreamReader != null) {
          try {
            inputStreamReader.close();
          }
 catch (          IOException e) {
          }
        }
      }
    }
  }
  return null;
}
