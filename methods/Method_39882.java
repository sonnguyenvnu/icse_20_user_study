/** 
 * Creates and returns a ServletOutputStream to write the content associated with this Response.
 */
public ServletOutputStream createOutputStream() throws IOException {
  GzipResponseStream gzstream=new GzipResponseStream(origResponse);
  gzstream.setBuffer(threshold);
  return gzstream;
}
