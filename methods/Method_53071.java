/** 
 * Returns the value on the top of the stack.
 */
private Scope peek() throws JSONException {
  if (stack.isEmpty()) {
    throw new JSONException("Nesting problem");
  }
  return stack.get(stack.size() - 1);
}
