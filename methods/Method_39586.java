/** 
 * Pops as many abstract types from the output frame stack as described by the given descriptor.
 * @param descriptor a type or method descriptor (in which case its argument types are popped).
 */
private void pop(final String descriptor){
  char firstDescriptorChar=descriptor.charAt(0);
  if (firstDescriptorChar == '(') {
    pop((Type.getArgumentsAndReturnSizes(descriptor) >> 2) - 1);
  }
 else   if (firstDescriptorChar == 'J' || firstDescriptorChar == 'D') {
    pop(2);
  }
 else {
    pop(1);
  }
}
