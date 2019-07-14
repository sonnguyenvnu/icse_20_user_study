/** 
 * Returns the response stream.<br> This method cannot be called after calling asString() or asDcoument()<br> It is suggested to call disconnect() after consuming the stream. <p> Disconnects the internal HttpURLConnection silently.
 * @return response body stream
 * @see #disconnect()
 */
public InputStream asStream(){
  if (streamConsumed) {
    throw new IllegalStateException("Stream has already been consumed.");
  }
  return is;
}
