/** 
 * Pops the given number of abstract types from the output frame stack.
 * @param elements the number of abstract types that must be popped.
 */
private void pop(final int elements){
  if (outputStackTop >= elements) {
    outputStackTop-=elements;
  }
 else {
    outputStackStart-=elements - outputStackTop;
    outputStackTop=0;
  }
}
