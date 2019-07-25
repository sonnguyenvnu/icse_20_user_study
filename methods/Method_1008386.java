/** 
 * Constructs a new keystore with the given password. 
 */
public static KeyStoreWrapper create(char[] password) throws Exception {
  KeyStoreWrapper wrapper=new KeyStoreWrapper(FORMAT_VERSION,password.length != 0,NEW_KEYSTORE_TYPE,NEW_KEYSTORE_STRING_KEY_ALGO,NEW_KEYSTORE_FILE_KEY_ALGO,new HashMap<>(),null);
  KeyStore keyStore=KeyStore.getInstance(NEW_KEYSTORE_TYPE);
  keyStore.load(null,null);
  wrapper.keystore.set(keyStore);
  wrapper.keystorePassword.set(new KeyStore.PasswordProtection(password));
  addBootstrapSeed(wrapper);
  return wrapper;
}
