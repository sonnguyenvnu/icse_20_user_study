/** 
 * Alters the stack to contain one less element and return it.
 * @return the element popped from the stack
 */
public Type pop(){
  if (top < 1)   throw new IndexOutOfBoundsException("Stack is empty");
  return stack[--top];
}
