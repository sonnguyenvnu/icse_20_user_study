/** 
 * The default redirect issuing strategy. <p> Ratpack makes this redirector available via the base server registry, making it the default. <p> This redirector always issues absolute URLs, interpreted from the  {@code toString()} value of the given {@code to} value.One exception to this rule is the case of  {@link java.net.URI}, where the  {@link URI#toASCIIString()} is used.<p> The string value is transformed into an absolute URL in the following ways: <ol> <li>starts with  {@code http://} or {@code https://} (<i>literal URL</i>) - value is used without modification</li><li>starts with  {@code //} (<i>protocol relative</i>) - current public request protocol is prepended (based on {@link PublicAddress}) <li>starts with  {@code /} (<i>absolute path</i>) - protocol, host and port are prepended (based on {@link PublicAddress}) <li>else (<i>relative path</i>) - protocol, host, port and current request path are prepended (based on  {@link PublicAddress} and {@link Request#getPath()}) </ol>
 * @return the standard redirector
 * @since 1.3
 */
static Redirector standard(){
  return DefaultRedirector.INSTANCE;
}
