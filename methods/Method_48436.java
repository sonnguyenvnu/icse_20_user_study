public static <E>EntryList ofByteBuffer(Iterator<E> elements,StaticArrayEntry.GetColVal<E,ByteBuffer> getter){
  return of(elements,getter,StaticArrayEntry.ByteBufferHandler.INSTANCE);
}
