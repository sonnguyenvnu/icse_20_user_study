public static <E>Iterator<Entry> makeEntryIterator(final Iterable<E> entries,final StaticArrayEntry.GetColVal<E,ByteBuffer> getter,final StaticBuffer lastColumn,final int limit){
  return Iterators.transform(Iterators.filter(entries.iterator(),new FilterResultColumns<>(lastColumn,limit,getter)),new Function<E,Entry>(){
    @Nullable @Override public Entry apply(    @Nullable E e){
      return StaticArrayEntry.ofByteBuffer(e,getter);
    }
  }
);
}
