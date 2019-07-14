/** 
 * Some fields contain multiple value types which can be isolated by applying a bitmask. That method returns the concatenation of active values.
 * @param valueNames The array containing the different values and names for thisfield. Must contain mask values too.
 * @param bitfield   The bitfield to inspect.
 * @return The names of the different values contained in the bitfield,separated by '+'.
 */
@NonNull private String activeFlags(@NonNull SparseArray<String> valueNames,int bitfield){
  final StringBuilder result=new StringBuilder();
  for (int i=0; i < valueNames.size(); i++) {
    final int maskValue=valueNames.keyAt(i);
    if (valueNames.get(maskValue).endsWith(SUFFIX_MASK)) {
      final int value=bitfield & maskValue;
      if (value > 0) {
        if (result.length() > 0) {
          result.append('+');
        }
        result.append(valueNames.get(value));
      }
    }
  }
  return result.toString();
}
