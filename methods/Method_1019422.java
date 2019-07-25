/** 
 * Creates a  {@link SSLConfiguration} instance with a string.<br>The string should format as: <br> <pre> KEYS_TORE_PATH,KEY_STORE_PASSWORD,TRUST_KEY_STORE_PATH,TRUST_KEY_STORE_PASSWORD,CLIENT_AUTH </pre>
 * @param configuration configuration as a string.
 * @return the instance of {@link SSLConfiguration}.
 */
public static SSLConfiguration parse(String configuration){
  String[] strings=configuration.split(",");
  if (strings.length == 2) {
    KeyStoreInfo keyStoreInfo=new KeyStoreInfo(strings[0],strings[1]);
    return new SSLConfiguration(keyStoreInfo,null);
  }
 else   if (strings.length == 4) {
    KeyStoreInfo keyStoreInfo=new KeyStoreInfo(strings[0],strings[1]);
    KeyStoreInfo trustKeyStoreInfo=new KeyStoreInfo(strings[2],strings[3]);
    return new SSLConfiguration(keyStoreInfo,trustKeyStoreInfo);
  }
 else   if (strings.length == 5) {
    KeyStoreInfo keyStoreInfo=new KeyStoreInfo(strings[0],strings[1]);
    KeyStoreInfo trustKeyStoreInfo=new KeyStoreInfo(strings[2],strings[3]);
    return new SSLConfiguration(keyStoreInfo,trustKeyStoreInfo,strings[4].equals("true"));
  }
  return null;
}
