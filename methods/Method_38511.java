/** 
 * Trusts all certificates, use with caution.
 */
public HttpRequest trustAllCerts(final boolean trust){
  trustAllCertificates=trust;
  return this;
}
