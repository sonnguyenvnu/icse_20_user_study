/** 
 * Pops the top element off of the stack.
 * @return {@code non-null;} the type formerly on the top of the stack
 * @throws SimException thrown if the stack is empty
 */
public TypeBearer pop(){
  throwIfImmutable();
  TypeBearer result=peek(0);
  stack[stackPtr - 1]=null;
  local[stackPtr - 1]=false;
  stackPtr-=result.getType().getCategory();
  return result;
}
