/** 
 * Read the specified key as a  {@link ByteSource} using the default Consul agent connection properties and the provided {@link QueryOptions}.
 * @param key the key to read from Consul Key-Value store
 * @param queryOptions the options to use when querying Consul
 * @return a {@link ByteSource} representing the value stored in the key
 * @see #value(String,QueryOptions,Action)
 */
static ByteSource value(String key,QueryOptions queryOptions){
  return value(key,queryOptions,Action.noop());
}
