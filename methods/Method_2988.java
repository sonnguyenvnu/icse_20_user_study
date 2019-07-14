/** 
 * View top element of stack
 * @return
 */
public int stackTop(){
  if (stack.size() > 0)   return stack.peek();
  return -1;
}
