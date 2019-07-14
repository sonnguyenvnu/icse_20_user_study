/** 
 * Provides a copy of keys. 
 */
public @Nullable int[] copyKeys(){
  if (size() == 0) {
    return null;
  }
  return Arrays.copyOf(mKeys,size());
}
