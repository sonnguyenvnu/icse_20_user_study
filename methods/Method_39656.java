/** 
 * Returns the start index of the return type of the given method descriptor.
 * @param methodDescriptor a method descriptor.
 * @return the start index of the return type of the given method descriptor.
 */
static int getReturnTypeOffset(final String methodDescriptor){
  int currentOffset=1;
  while (methodDescriptor.charAt(currentOffset) != ')') {
    while (methodDescriptor.charAt(currentOffset) == '[') {
      currentOffset++;
    }
    if (methodDescriptor.charAt(currentOffset++) == 'L') {
      currentOffset=methodDescriptor.indexOf(';',currentOffset) + 1;
    }
  }
  return currentOffset + 1;
}
