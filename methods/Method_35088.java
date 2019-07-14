public static <Raw,Key>void clearPersister(BasePersister persister,@Nonnull Key key){
  boolean isPersisterClearable=persister instanceof Clearable;
  if (isPersisterClearable) {
    ((Clearable<Key>)persister).clear(key);
  }
}
