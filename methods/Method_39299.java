/** 
 * Controls the behavior of getInternetAddress. If true, allows the real world practice of: <ul> <li>&lt;bob@example.com&gt; (Bob Smith)</li> </ul> <p> In this case, &quot;Bob Smith&quot; is not technically the personal name, just a comment. If this is set to true, the methods will convert this into: <ul> <li>Bob Smith &lt;bob@example.com&gt;</li> </ul> <p> This also happens somewhat more often and appropriately with <code>mailer-daemon@blah.com (Mail Delivery System)</code>. <p> <p> If a personal name appears to the left and CFWS appears to the right of an address, the methods will favor the personal name to the left. If the methods need to use the CFWS following the address, they will take the first comment token they find.
 */
public RFC2822AddressParser extractCfwsPersonalName(final boolean extract){
  EXTRACT_CFWS_PERSONAL_NAMES=extract;
  resetPatterns();
  return this;
}
