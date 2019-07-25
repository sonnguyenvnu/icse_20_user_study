/** 
 * Read the specified key as a  {@link ByteSource} using the specified configuration to connection to Consul.
 * @param key the key to read from Consul's KeyValue store
 * @param clientConfig the configuration for the Consul connection
 * @return a {@link ByteSource} representing the value stored in the key
 * @see #value(String,QueryOptions,Action)
 */
static ByteSource value(String key,Action<? super Consul.Builder> clientConfig){
  return value(key,QueryOptions.BLANK,clientConfig);
}
