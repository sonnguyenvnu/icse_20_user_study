/** 
 * Copies all the converters in the set to the given array.
 */
void copyInto(Converter[] converters){
  System.arraycopy(iConverters,0,converters,0,iConverters.length);
}
