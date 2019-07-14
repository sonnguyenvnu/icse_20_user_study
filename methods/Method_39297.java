/** 
 * Changes the behavior of the domain parsing. If  {@code true}, the parser will allow 2822 domains, which include single-level domains (e.g. bob@localhost) as well as domain literals, e.g.: <p> <ul> <li><code>someone@[192.168.1.100]</code> or</li> <li><code>john.doe@[23:33:A2:22:16:1F]</code> or</li> <li><code>me@[my computer]</code></li> </ul> <p> The RFC says these are valid email addresses, but many don't like allowing them. If you don't want to allow them, and only want to allow valid domain names (<a href="http://www.ietf.org/rfc/rfc1035.txt">RFC 1035</a>, x.y.z.com, etc), and specifically only those with at least two levels ("example.com"), then set this flag to  {@code false}.
 */
public RFC2822AddressParser allowDomainLiterals(final boolean allow){
  ALLOW_DOMAIN_LITERALS=allow;
  resetPatterns();
  return this;
}
