/** 
 * When enabled, <code>MAIL_SMTP_SOCKET_FACTORY_CLASS</code> will be not set, and Plaintext Authentication over TLS will be enabled.
 * @param plaintextOverTLS {@code true} when plain text authentication over TLS should be enabled.
 * @return this
 */
public SmtpSslServer plaintextOverTLS(final boolean plaintextOverTLS){
  this.plaintextOverTLS=plaintextOverTLS;
  return this;
}
