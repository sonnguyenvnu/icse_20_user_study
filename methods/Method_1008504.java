@Override public AtomicNumericFieldData load(LeafReaderContext context){
  final LeafReader reader=context.reader();
  final String field=fieldName;
switch (numericType) {
case HALF_FLOAT:
    return new SortedNumericHalfFloatFieldData(reader,field);
case FLOAT:
  return new SortedNumericFloatFieldData(reader,field);
case DOUBLE:
return new SortedNumericDoubleFieldData(reader,field);
default :
return new SortedNumericLongFieldData(reader,field,numericType);
}
}
