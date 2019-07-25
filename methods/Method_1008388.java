/** 
 * Decrypts the underlying java keystore. This may only be called once. The provided password will be zeroed out.
 */
public void decrypt(char[] password) throws GeneralSecurityException, IOException {
  if (keystore.get() != null) {
    throw new IllegalStateException("Keystore has already been decrypted");
  }
  keystore.set(KeyStore.getInstance(type));
  try (InputStream in=new ByteArrayInputStream(keystoreBytes)){
    keystore.get().load(in,password);
  }
  finally {
    Arrays.fill(keystoreBytes,(byte)0);
  }
  keystorePassword.set(new KeyStore.PasswordProtection(password));
  Arrays.fill(password,'\0');
  Enumeration<String> aliases=keystore.get().aliases();
  if (formatVersion == 1) {
    while (aliases.hasMoreElements()) {
      settingTypes.put(aliases.nextElement(),KeyType.STRING);
    }
  }
 else {
    Set<String> expectedSettings=new HashSet<>(settingTypes.keySet());
    while (aliases.hasMoreElements()) {
      String settingName=aliases.nextElement();
      if (expectedSettings.remove(settingName) == false) {
        throw new SecurityException("Keystore has been corrupted or tampered with");
      }
    }
    if (expectedSettings.isEmpty() == false) {
      throw new SecurityException("Keystore has been corrupted or tampered with");
    }
  }
}
