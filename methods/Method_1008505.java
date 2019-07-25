@Override public AtomicOrdinalsFieldData load(LeafReaderContext context){
  return new SortedSetDVBytesAtomicFieldData(context.reader(),fieldName,scriptFunction);
}
