/** 
 * Puts some abstract types of  {@link #currentFrame} in {@link #stackMapTableEntries} , using theJVMS verification_type_info format used in StackMapTable attributes.
 * @param start index of the first type in {@link #currentFrame} to write.
 * @param end index of last type in {@link #currentFrame} to write (exclusive).
 */
private void putAbstractTypes(final int start,final int end){
  for (int i=start; i < end; ++i) {
    Frame.putAbstractType(symbolTable,currentFrame[i],stackMapTableEntries);
  }
}
