/** 
 * Registers  {@link jodd.http.HttpProgressListener listener} that willmonitor upload progress. Be aware that the whole size of the request is being monitored, not only the files content.
 */
public HttpRequest monitor(final HttpProgressListener httpProgressListener){
  this.httpProgressListener=httpProgressListener;
  return this;
}
