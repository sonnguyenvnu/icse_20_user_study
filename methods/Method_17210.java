/** 
 * Returns access to perform operations based on the variable expiration policy. This policy determines that an entry should be automatically removed from the cache once a per-entry duration has elapsed. <p> If the cache was not constructed with variable expiration or the implementation does not support these operations, an empty  {@link Optional} is returned.
 * @return access to low-level operations for this cache if a variable expiration policy is used
 */
@NonNull default Optional<VarExpiration<K,V>> expireVariably(){
  return Optional.empty();
}
