/** 
 * Copies and optionally truncates an array. Prevents null array elements created by  {@link Arrays#copyOf(Object[],int)} by ensuring the new length does not exceed the current length.
 * @param input The input array.
 * @param length The output array length. Must be less or equal to the length of the input array.
 * @return The copied array.
 */
@SuppressWarnings({"nullness:argument.type.incompatible","nullness:return.type.incompatible"}) public static <T>T[] nullSafeArrayCopy(T[] input,int length){
  Assertions.checkArgument(length <= input.length);
  return Arrays.copyOf(input,length);
}
