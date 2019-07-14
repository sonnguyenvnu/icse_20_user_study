/** 
 * Assignees provided  {@link jodd.http.HttpConnection} for communication.It does not actually opens it until the  {@link #send() sending}.
 */
public HttpRequest open(final HttpConnection httpConnection){
  if (this.httpConnection != null) {
    throw new HttpException("Connection already opened");
  }
  this.httpConnection=httpConnection;
  this.httpConnectionProvider=null;
  return this;
}
