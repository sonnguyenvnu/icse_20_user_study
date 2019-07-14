/** 
 * Registers entity. Existing entity will be removed if exist, so no exception will be thrown. 
 */
public <E>DbEntityDescriptor<E> registerEntity(final Class<E> type,final boolean force){
  if (force) {
    removeEntity(type);
  }
  return registerEntity(type);
}
