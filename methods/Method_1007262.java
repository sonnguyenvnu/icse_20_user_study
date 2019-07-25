/** 
 * Gets the top of the stack without altering it
 * @return the top of the stack
 */
public Type peek(){
  if (top < 1)   throw new IndexOutOfBoundsException("Stack is empty");
  return stack[top - 1];
}
