/** 
 * Replace the value on the top of the stack with the given value.
 */
private void replaceTop(Scope topOfStack){
  stack.set(stack.size() - 1,topOfStack);
}
