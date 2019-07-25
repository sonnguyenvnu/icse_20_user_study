@Override public BinaryDVAtomicFieldData load(LeafReaderContext context){
  return new BinaryDVAtomicFieldData(context.reader(),fieldName);
}
