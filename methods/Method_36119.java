@Override public HttpsSettings httpsSettings(){
  return new HttpsSettings.Builder().port(httpsPortNumber()).keyStorePath((String)optionSet.valueOf(HTTPS_KEYSTORE)).keyStorePassword((String)optionSet.valueOf(HTTPS_KEYSTORE_PASSWORD)).trustStorePath((String)optionSet.valueOf(HTTPS_TRUSTSTORE)).trustStorePassword((String)optionSet.valueOf(HTTPS_TRUSTSTORE_PASSWORD)).needClientAuth(optionSet.has(REQUIRE_CLIENT_CERT)).build();
}
