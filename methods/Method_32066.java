/** 
 * Returns a copy of this set, with the converter at the given index removed.
 * @param index index of converter to remove
 * @param removed if not null, element 0 is set to the removed converter
 * @throws IndexOutOfBoundsException if the index is invalid
 */
ConverterSet remove(final int index,Converter[] removed){
  Converter[] converters=iConverters;
  int length=converters.length;
  if (index >= length) {
    throw new IndexOutOfBoundsException();
  }
  if (removed != null) {
    removed[0]=converters[index];
  }
  Converter[] copy=new Converter[length - 1];
  int j=0;
  for (int i=0; i < length; i++) {
    if (i != index) {
      copy[j++]=converters[i];
    }
  }
  return new ConverterSet(copy);
}
