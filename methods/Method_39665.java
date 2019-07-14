/** 
 * Computes the size of the arguments and of the return value of a method.
 * @param methodDescriptor a method descriptor.
 * @return the size of the arguments of the method (plus one for the implicit this argument),argumentsSize, and the size of its return value, returnSize, packed into a single int i = {@code (argumentsSize &lt;&lt; 2) | returnSize} (argumentsSize is therefore equal to {@code i &gt;&gt; 2}, and returnSize to  {@code i &amp; 0x03}).
 */
public static int getArgumentsAndReturnSizes(final String methodDescriptor){
  int argumentsSize=1;
  int currentOffset=1;
  int currentChar=methodDescriptor.charAt(currentOffset);
  while (currentChar != ')') {
    if (currentChar == 'J' || currentChar == 'D') {
      currentOffset++;
      argumentsSize+=2;
    }
 else {
      while (methodDescriptor.charAt(currentOffset) == '[') {
        currentOffset++;
      }
      if (methodDescriptor.charAt(currentOffset++) == 'L') {
        currentOffset=methodDescriptor.indexOf(';',currentOffset) + 1;
      }
      argumentsSize+=1;
    }
    currentChar=methodDescriptor.charAt(currentOffset);
  }
  currentChar=methodDescriptor.charAt(currentOffset + 1);
  if (currentChar == 'V') {
    return argumentsSize << 2;
  }
 else {
    int returnSize=(currentChar == 'J' || currentChar == 'D') ? 2 : 1;
    return argumentsSize << 2 | returnSize;
  }
}
