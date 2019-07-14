/** 
 * Converts non-array value to array. Detects various types and collections, iterates them to make conversion and to create target array.
 */
protected byte[] convertValueToArray(final Object value){
  if (value instanceof Blob) {
    final Blob blob=(Blob)value;
    try {
      final long length=blob.length();
      if (length > Integer.MAX_VALUE) {
        throw new TypeConversionException("Blob is too big.");
      }
      return blob.getBytes(1,(int)length);
    }
 catch (    SQLException sex) {
      throw new TypeConversionException(value,sex);
    }
  }
  if (value instanceof File) {
    try {
      return FileUtil.readBytes((File)value);
    }
 catch (    IOException ioex) {
      throw new TypeConversionException(value,ioex);
    }
  }
  if (value instanceof Collection) {
    final Collection collection=(Collection)value;
    final byte[] target=new byte[collection.size()];
    int i=0;
    for (    final Object element : collection) {
      target[i]=convertType(element);
      i++;
    }
    return target;
  }
  if (value instanceof Iterable) {
    final Iterable iterable=(Iterable)value;
    final ArrayList<Byte> byteArrayList=new ArrayList<>();
    for (    final Object element : iterable) {
      final byte convertedValue=convertType(element);
      byteArrayList.add(Byte.valueOf(convertedValue));
    }
    final byte[] array=new byte[byteArrayList.size()];
    for (int i=0; i < byteArrayList.size(); i++) {
      final Byte b=byteArrayList.get(i);
      array[i]=b.byteValue();
    }
    return array;
  }
  if (value instanceof CharSequence) {
    final String[] strings=StringUtil.splitc(value.toString(),ArrayConverter.NUMBER_DELIMITERS);
    return convertArrayToArray(strings);
  }
  return convertToSingleElementArray(value);
}
