/** 
 * Inserts any necessary separators and whitespace before a name. Also adjusts the stack to expect the key's value.
 */
private void beforeKey() throws JSONException {
  Scope context=peek();
  if (context == Scope.NONEMPTY_OBJECT) {
    out.append(',');
  }
 else   if (context != Scope.EMPTY_OBJECT) {
    throw new JSONException("Nesting problem");
  }
  newline();
  replaceTop(Scope.DANGLING_KEY);
}
