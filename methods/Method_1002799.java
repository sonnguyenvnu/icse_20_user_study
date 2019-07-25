/** 
 * Adds an  {@link Authorizer}.
 */
public HttpAuthServiceBuilder add(Authorizer<HttpRequest> authorizer){
  requireNonNull(authorizer,"authorizer");
  if (this.authorizer == null) {
    this.authorizer=authorizer;
  }
 else {
    this.authorizer=this.authorizer.orElse(authorizer);
  }
  return this;
}
