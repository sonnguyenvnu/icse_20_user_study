/** 
 * Read the specified key as a  {@link ByteSource} using the default Consul agent connection properties.
 * @param key the key to read from Consul's Key-Value store
 * @return a {@link ByteSource} representing the value stored in the key
 * @see #value(String,QueryOptions,Action)
 */
static ByteSource value(String key){
  return value(key,QueryOptions.BLANK,Action.noop());
}
