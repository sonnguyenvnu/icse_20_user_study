int read(final byte[] data,final int startOffset){
  int offset=startOffset;
  _header=new ArrayHeader(data,offset);
  offset+=_header.getSize();
  long numberOfScalarsLong=_header.getNumberOfScalarValues();
  if (numberOfScalarsLong > Integer.MAX_VALUE)   throw new UnsupportedOperationException("Sorry, but POI can't store array of properties with size of " + numberOfScalarsLong + " in memory");
  int numberOfScalars=(int)numberOfScalarsLong;
  _values=new TypedPropertyValue[numberOfScalars];
  final int type=_header._type;
  if (type == Variant.VT_VARIANT) {
    for (int i=0; i < numberOfScalars; i++) {
      TypedPropertyValue typedPropertyValue=new TypedPropertyValue();
      offset+=typedPropertyValue.read(data,offset);
    }
  }
 else {
    for (int i=0; i < numberOfScalars; i++) {
      TypedPropertyValue typedPropertyValue=new TypedPropertyValue(type,null);
      offset+=typedPropertyValue.readValuePadded(data,offset);
    }
  }
  return offset - startOffset;
}
