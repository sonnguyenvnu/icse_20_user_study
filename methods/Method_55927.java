/** 
 * Returns a  {@link PointerBuffer} view of the data pointed to by the {@code kernelParams} field.
 * @param capacity the number of elements in the returned buffer
 */
@NativeType("void **") public PointerBuffer kernelParams(int capacity){
  return nkernelParams(address(),capacity);
}
