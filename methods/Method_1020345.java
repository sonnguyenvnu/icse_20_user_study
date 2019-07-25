/** 
 * Changes an element already on a stack. This method is useful in limited contexts, particularly when merging two instances. As such, it places the following restriction on its behavior: You may only replace values with other values of the same category.
 * @param n {@code >= 0;} which element to change, where {@code 0} isthe top element of the stack
 * @param type {@code non-null;} type of the new value
 * @throws SimException thrown if {@code n >= size()} orthe action is otherwise prohibited
 */
public void change(int n,TypeBearer type){
  throwIfImmutable();
  try {
    type=type.getFrameType();
  }
 catch (  NullPointerException ex) {
    throw new NullPointerException("type == null");
  }
  int idx=stackPtr - n - 1;
  TypeBearer orig=stack[idx];
  if ((orig == null) || (orig.getType().getCategory() != type.getType().getCategory())) {
    throwSimException("incompatible substitution: " + stackElementString(orig) + " -> " + stackElementString(type));
  }
  stack[idx]=type;
}
