/** 
 * Peeks at the  {@code n}th element down from the top of the stack. {@code n == 0} means to peek at the top of the stack. Note thatthis will return  {@code null} if the indicated element is thedeeper half of a category-2 value.
 * @param n {@code >= 0;} which element to peek at
 * @return {@code null-ok;} the type of value stored at that element
 * @throws SimException thrown if {@code n >= size()}
 */
public TypeBearer peek(int n){
  if (n < 0) {
    throw new IllegalArgumentException("n < 0");
  }
  if (n >= stackPtr) {
    return throwSimException("underflow");
  }
  return stack[stackPtr - n - 1];
}
