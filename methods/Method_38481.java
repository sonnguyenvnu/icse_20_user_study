/** 
 * Sends request or response to output stream.
 */
public void sendTo(final OutputStream out) throws IOException {
  final Buffer buffer=buffer(true);
  if (httpProgressListener == null) {
    buffer.writeTo(out);
  }
 else {
    buffer.writeTo(out,httpProgressListener);
  }
  out.flush();
}
