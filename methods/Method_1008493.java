@Override public AtomicOrdinalsFieldData load(LeafReaderContext context){
  return atomicReaders[context.ord];
}
