/** 
 * Specifies the domain within which this cookie should be presented. <p> The form of the domain name is specified by RFC 2109. A domain name begins with a dot (<code>.foo.com</code>) and means that the cookie is visible to servers in a specified Domain Name System (DNS) zone (for example, <code>www.foo.com</code>, but not <code>a.b.foo.com</code>). By default, cookies are only returned to the server that sent them.
 */
public Cookie setDomain(final String pattern){
  domain=pattern.toLowerCase();
  return this;
}
