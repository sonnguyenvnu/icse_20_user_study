@Override public BytesBinaryDVAtomicFieldData load(LeafReaderContext context){
  try {
    return new BytesBinaryDVAtomicFieldData(DocValues.getBinary(context.reader(),fieldName));
  }
 catch (  IOException e) {
    throw new IllegalStateException("Cannot load doc values",e);
  }
}
