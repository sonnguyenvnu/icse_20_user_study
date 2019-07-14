/** 
 * Constructs a new  {@code CacheBuilder} instance with default settings, including strong keys,strong values, and no automatic eviction of any kind.
 */
@Nonnull public static CacheBuilder<Object,Object> newBuilder(){
  return new CacheBuilder<>();
}
