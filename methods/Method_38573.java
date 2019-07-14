/** 
 * Returns <code>true</code> if entity is persisted. i.e. {@link #getEntityId() ID} is not <code>0</code>
 */
default boolean isPersistent(){
  return getEntityId() != null;
}
