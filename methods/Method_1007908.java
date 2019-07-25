/** 
 * Put a diagnostic context value (the <code>val</code> parameter) as identified with the <code>key</code> parameter into the current thread's diagnostic context map. The <code>key</code> parameter cannot be null. The <code>val</code> parameter can be null only if the underlying implementation supports it. <p> This method delegates all work to the MDC of the underlying logging system.
 * @param key non-null key 
 * @param val value to put in the map
 * @throws IllegalArgumentException in case the "key" parameter is null
 */
public static void put(String key,String val) throws IllegalArgumentException {
  if (key == null) {
    throw new IllegalArgumentException("key parameter cannot be null");
  }
  if (mdcAdapter == null) {
    throw new IllegalStateException("MDCAdapter cannot be null. See also " + NULL_MDCA_URL);
  }
  mdcAdapter.put(key,val);
}
