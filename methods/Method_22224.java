/** 
 * Some fields contain multiple value types which can be isolated by applying a bitmask. That method returns the concatenation of active values.
 * @param bitfield The bitfield to inspect.
 * @return The names of the different values contained in the bitfield,separated by '+'.
 */
@NonNull private String activeFlags(SparseArray<String> flagNames,int bitfield){
  final StringBuilder result=new StringBuilder();
  for (int i=0; i < flagNames.size(); i++) {
    final int maskValue=flagNames.keyAt(i);
    final int value=bitfield & maskValue;
    if (value > 0) {
      if (result.length() > 0) {
        result.append('+');
      }
      result.append(flagNames.get(value));
    }
  }
  return result.toString();
}
