/** 
 * Creates a custom  {@link HostnameVerifier} that allows a specific certificate to be accepted fora mismatching hostname.
 * @param requestHostname hostname used to access the service which offers the incorrectly namedcertificate
 * @param certPrincipalName RFC 2253 name on the certificate
 * @return A {@link HostnameVerifier} that will accept the provided combination of names
 */
public static HostnameVerifier createIncorrectHostnameVerifier(final String requestHostname,final String certPrincipalName){
  return new HostnameVerifier(){
    @Override public boolean verify(    String hostname,    SSLSession session){
      try {
        String principalName=session.getPeerPrincipal().getName();
        if (hostname.equals(requestHostname) && principalName.equals(certPrincipalName))         return true;
      }
 catch (      SSLPeerUnverifiedException e) {
      }
      return HttpsURLConnection.getDefaultHostnameVerifier().verify(hostname,session);
    }
  }
;
}
