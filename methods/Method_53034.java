/** 
 * Returns the response body as string.<br> Disconnects the internal HttpURLConnection silently.
 * @return response body
 * @throws TwitterException when there is any network issue upon response body consumption
 */
public String asString() throws TwitterException {
  if (null == responseAsString) {
    BufferedReader br=null;
    InputStream stream=null;
    try {
      stream=asStream();
      if (null == stream) {
        return null;
      }
      br=new BufferedReader(new InputStreamReader(stream,"UTF-8"));
      StringBuilder buf=new StringBuilder();
      String line;
      while ((line=br.readLine()) != null) {
        buf.append(line).append("\n");
      }
      this.responseAsString=buf.toString();
      logger.debug(responseAsString);
      stream.close();
      streamConsumed=true;
    }
 catch (    IOException ioe) {
      throw new TwitterException(ioe.getMessage(),ioe);
    }
 finally {
      if (stream != null) {
        try {
          stream.close();
        }
 catch (        IOException ignore) {
        }
      }
      if (br != null) {
        try {
          br.close();
        }
 catch (        IOException ignore) {
        }
      }
      disconnectForcibly();
    }
  }
  return responseAsString;
}
