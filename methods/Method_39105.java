/** 
 * Returns  {@code true} if path chunk value matches the input.
 */
public boolean match(final String value){
  if (pathMacros == null) {
    return this.value.equals(value);
  }
  return pathMacros.match(value) != -1;
}
