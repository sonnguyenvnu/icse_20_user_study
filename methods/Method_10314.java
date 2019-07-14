/** 
 * Gets a Default KeyStore
 * @return KeyStore
 */
public static KeyStore getKeystore(){
  KeyStore trustStore=null;
  try {
    trustStore=KeyStore.getInstance(KeyStore.getDefaultType());
    trustStore.load(null,null);
  }
 catch (  Throwable t) {
    t.printStackTrace();
  }
  return trustStore;
}
