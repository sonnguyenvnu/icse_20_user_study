/** 
 * Returns an SSL client for this host's localhost address. 
 */
public static synchronized HandshakeCertificates localhost(){
  if (localhost != null)   return localhost;
  try {
    HeldCertificate heldCertificate=new HeldCertificate.Builder().rsa2048().commonName("localhost").addSubjectAlternativeName(InetAddress.getByName("localhost").getCanonicalHostName()).build();
    localhost=new HandshakeCertificates.Builder().heldCertificate(heldCertificate).addTrustedCertificate(heldCertificate.certificate()).build();
    return localhost;
  }
 catch (  UnknownHostException e) {
    throw new RuntimeException(e);
  }
}
