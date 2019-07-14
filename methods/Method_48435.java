public static <E>EntryList ofStaticBuffer(Iterable<E> elements,StaticArrayEntry.GetColVal<E,StaticBuffer> getter){
  return of(elements,getter,StaticArrayEntry.StaticBufferHandler.INSTANCE);
}
