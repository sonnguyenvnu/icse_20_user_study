public static <E>EntryList ofBytes(Iterable<E> elements,StaticArrayEntry.GetColVal<E,byte[]> getter){
  return of(elements,getter,StaticArrayEntry.ByteArrayHandler.INSTANCE);
}
