/** 
 * Pushes a value of the given type onto the stack.
 * @param type {@code non-null;} type of the value
 * @throws SimException thrown if there is insufficient room on thestack for the value
 */
public void push(TypeBearer type){
  throwIfImmutable();
  int category;
  try {
    type=type.getFrameType();
    category=type.getType().getCategory();
  }
 catch (  NullPointerException ex) {
    throw new NullPointerException("type == null");
  }
  if ((stackPtr + category) > stack.length) {
    throwSimException("overflow");
    return;
  }
  if (category == 2) {
    stack[stackPtr]=null;
    stackPtr++;
  }
  stack[stackPtr]=type;
  stackPtr++;
}
