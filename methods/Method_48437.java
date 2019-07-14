public static <E>EntryList ofStaticBuffer(Iterator<E> elements,StaticArrayEntry.GetColVal<E,StaticBuffer> getter){
  return of(elements,getter,StaticArrayEntry.StaticBufferHandler.INSTANCE);
}
