/** 
 * Pops an abstract type from the output frame stack and returns its value.
 * @return the abstract type that has been popped from the output frame stack.
 */
private int pop(){
  if (outputStackTop > 0) {
    return outputStack[--outputStackTop];
  }
 else {
    return STACK_KIND | -(--outputStackStart);
  }
}
