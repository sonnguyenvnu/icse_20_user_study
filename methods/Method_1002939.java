/** 
 * Creates a new  {@link KeyStoreCredentialResolver}.
 */
public CredentialResolver build() throws IOException, GeneralSecurityException {
  final KeyStore ks=KeyStore.getInstance(type);
  try (InputStream is=open()){
    ks.load(is,password != null ? password.toCharArray() : null);
  }
   return new KeyStoreCredentialResolver(ks,keyPasswords);
}
