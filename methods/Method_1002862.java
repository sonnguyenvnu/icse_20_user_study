/** 
 * Binds the specified  {@link Service} at the specified path pattern of the default {@link VirtualHost}. e.g. <ul> <li> {@code /login} (no path parameters)</li><li> {@code} /users/userId}} (curly-brace style)</li> <li> {@code /list/:productType/by/:ordering} (colon style)</li><li> {@code exact:/foo/bar} (exact match)</li><li> {@code prefix:/files} (prefix match)</li><li><code>glob:/~&#42;/downloads/**</code> (glob pattern)</li> <li> {@code regex:^/files/(?<filePath>.*)$} (regular expression)</li></ul>
 * @throws IllegalArgumentException if the specified path pattern is invalid
 */
public ServerBuilder service(String pathPattern,Service<HttpRequest,HttpResponse> service){
  defaultVirtualHostBuilder.service(pathPattern,service);
  return this;
}
