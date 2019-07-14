public OAuthSignatureMethod getSignatureMethod(String methodName,SignatureSecret signatureSecret,String tokenSecret) throws UnsupportedSignatureMethodException {
  if (supportPlainText && PlainTextSignatureMethod.SIGNATURE_NAME.equals(methodName)) {
    if (!(signatureSecret instanceof SharedConsumerSecret)) {
      throw new IllegalArgumentException("Invalid secret for signature method " + methodName + ". Expected a " + SharedConsumerSecret.class.getName() + ", got " + (signatureSecret == null ? "null" : signatureSecret.getClass().getName()) + ".");
    }
    String consumerSecret=((SharedConsumerSecret)signatureSecret).getConsumerSecret();
    if (consumerSecret == null) {
      consumerSecret="";
    }
    if (tokenSecret == null) {
      tokenSecret="";
    }
    consumerSecret=oauthEncode(consumerSecret);
    tokenSecret=oauthEncode(tokenSecret);
    Object salt=null;
    if (signatureSecret instanceof SaltedConsumerSecret) {
      salt=((SaltedConsumerSecret)signatureSecret).getSalt();
    }
    return new PlainTextSignatureMethod(oauthEncode(new StringBuilder(consumerSecret).append('&').append(tokenSecret).toString()),this.plainTextPasswordEncoder,salt);
  }
 else   if (supportHMAC_SHA1 && HMAC_SHA1SignatureMethod.SIGNATURE_NAME.equals(methodName)) {
    if (!(signatureSecret instanceof SharedConsumerSecret)) {
      throw new IllegalArgumentException("Invalid secret for signature method " + methodName + ". Expected a " + SharedConsumerSecret.class.getName() + ", got " + (signatureSecret == null ? "null" : signatureSecret.getClass().getName()) + ".");
    }
    String consumerSecret=((SharedConsumerSecret)signatureSecret).getConsumerSecret();
    if (consumerSecret == null) {
      consumerSecret="";
    }
    if (tokenSecret == null) {
      tokenSecret="";
    }
    consumerSecret=oauthEncode(consumerSecret);
    tokenSecret=oauthEncode(tokenSecret);
    byte[] keyBytes;
    try {
      keyBytes=new StringBuilder(consumerSecret).append('&').append(tokenSecret).toString().getBytes("UTF-8");
    }
 catch (    UnsupportedEncodingException e) {
      throw new RuntimeException(e.getMessage());
    }
    SecretKeySpec spec=new SecretKeySpec(keyBytes,HMAC_SHA1SignatureMethod.MAC_NAME);
    return new HMAC_SHA1SignatureMethod(spec);
  }
 else   if (supportRSA_SHA1 && RSA_SHA1SignatureMethod.SIGNATURE_NAME.equals(methodName)) {
    if (signatureSecret instanceof RSAKeySecret) {
      PublicKey publicKey=((RSAKeySecret)signatureSecret).getPublicKey();
      PrivateKey privateKey=((RSAKeySecret)signatureSecret).getPrivateKey();
      return new RSA_SHA1SignatureMethod(privateKey,publicKey);
    }
 else {
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      if (authentication.getCredentials() instanceof X509Certificate) {
        X509Certificate certificate=(X509Certificate)authentication.getCredentials();
        if (certificate != null) {
          return new RSA_SHA1SignatureMethod(certificate.getPublicKey());
        }
      }
    }
  }
  throw new UnsupportedSignatureMethodException("Unsupported signature method: " + methodName);
}
